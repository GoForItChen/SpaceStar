/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.chen.mars.base.extension.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.chen.mars.base.extension.IService;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * IService ???????????? ?????????M ??? mapper ?????????T ????????? ???
 *
 * @author hubin
 * @since 2018-06-23
 */
@SuppressWarnings({"unchecked","unused"})
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected M baseMapper;

    public M getBaseMapper() {
        return baseMapper;
    }

    protected Class<T> entityClass = currentModelClass();

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * ???????????? ??????
     *
     * @return QueryWrapper ????????????
     */
    public QueryChainWrapper<T> query() {
        return ChainWrappers.queryChain(getBaseMapper());
    }

    /**
     * ???????????? lambda ???
     * <p>?????????????????? Kotlin </p>
     *
     * @return LambdaQueryWrapper ????????????
     */
    public LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(getBaseMapper());
    }

    /**
     * ???????????? lambda ???
     * kotlin ??????
     *
     * @return KtQueryWrapper ????????????
     */
    public KtQueryChainWrapper<T> ktQuery() {
        return ChainWrappers.ktQueryChain(getBaseMapper(), getEntityClass());
    }

    /**
     * ???????????? lambda ???
     * kotlin ??????
     *
     * @return KtQueryWrapper ????????????
     */
    public KtUpdateChainWrapper<T> ktUpdate() {
        return ChainWrappers.ktUpdateChain(getBaseMapper(), getEntityClass());
    }

    /**
     * ???????????? ??????
     *
     * @return UpdateWrapper ????????????
     */
    public UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(getBaseMapper());
    }

    /**
     * ???????????? lambda ???
     * ?????????????????? Kotlin
     *
     * @return LambdaUpdateWrapper ????????????
     */
    public LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(getBaseMapper());
    }

    /**
     * ??????updateWrapper??????????????????????????????saveOrUpdate(T)??????
     * ???????????????????????????????????????????????????????????????????????????????????????saveOrUpdate?????????
     *
     * @param entity ????????????
     */
    public boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        return update(entity, updateWrapper) || saveOrUpdate(entity);
    }

    protected Class<T> mapperClass = currentMapperClass();

    /**
     * ?????????????????????????????????
     *
     * @param result ?????????????????????????????????
     *
     * @return boolean
     *
     * @deprecated 3.3.1
     */
    @Deprecated
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentMapperClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * ???????????? SqlSession
     *
     * @deprecated 3.3.0
     */
    @Deprecated
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(entityClass);
    }

    /**
     * ??????sqlSession
     *
     * @param sqlSession session
     *
     * @deprecated 3.3.0
     */
    @Deprecated
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(entityClass));
    }

    /**
     * ?????? SqlStatement
     *
     * @param sqlMethod ignore
     *
     * @return ignore
     *
     * @see #getSqlStatement(SqlMethod)
     * @deprecated 3.4.0
     */
    @Deprecated
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(entityClass).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(getBaseMapper().insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return saveOrUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * ??????mapperStatementId
     *
     * @param sqlMethod ?????????
     *
     * @return ??????id
     *
     * @since 3.4.0
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)) ? save(entity) : updateById(entity);
        }
        return false;
    }

    @Override
    public T getById(Serializable id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return getBaseMapper().selectByMap(columnMap);
    }

    /**
     * ?????? Wrapper????????????????????? <br/>
     * <p>?????????????????????????????????????????????????????????????????????????????? wrapper.last("LIMIT 1")</p>
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public T getOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal)
                    || CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Override
    public boolean removeById(Serializable id) {
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return SqlHelper.retBool(getBaseMapper().deleteByMap(columnMap));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Wrapper<T> queryWrapper) {
        return SqlHelper.retBool(getBaseMapper().delete(queryWrapper));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    /**
     * ?????? UpdateWrapper ????????????????????? ????????????sqlset
     *
     * @param updateWrapper ??????????????????????????? {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    public boolean update(Wrapper<T> updateWrapper) {
        return update(null, updateWrapper);
    }

    /**
     * ?????? whereEntity ?????????????????????
     *
     * @param entity ????????????
     * @param updateWrapper ??????????????????????????? {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(getBaseMapper().update(entity, updateWrapper));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    /**
     * ?????? Wrapper?????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     * @param throwEx ????????? result ??????????????????
     */
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        if (throwEx) {
            return baseMapper.selectOne(queryWrapper);
        }
        return SqlHelper.getObject(log, baseMapper.selectList(queryWrapper));
    }

    /**
     * ?????? Wrapper?????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(log, baseMapper.selectMaps(queryWrapper));
    }

    /**
     * ?????? Wrapper?????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     * @param mapper ????????????
     */
    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(log, listObjs(queryWrapper, mapper));
    }

    @Override
    public int count() {
        return count(Wrappers.emptyWrapper());
    }

    /**
     * ?????? Wrapper ???????????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(getBaseMapper().selectCount(queryWrapper));
    }

    /**
     * ????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public List<T> list(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<T> list() {
        return list(Wrappers.emptyWrapper());
    }

    /**
     * ????????????
     *
     * @param page ????????????
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectPage(page, queryWrapper);
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        return page(page, Wrappers.emptyWrapper());
    }

    @Override
    public <E extends IPage<T>> E page(E page, T entity) {
        return getBaseMapper().selectPage(page, new QueryWrapper<>(entity));
    }
    /**
     * ????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectMaps(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return listMaps(Wrappers.emptyWrapper());
    }

    @Override
    public List<Object> listObjs() {
        return listObjs(Function.identity());
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return listObjs(Wrappers.emptyWrapper(), mapper);
    }

    /**
     * ?????? Wrapper ???????????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public List<Object> listObjs(Wrapper<T> queryWrapper) {
        return listObjs(queryWrapper, Function.identity());
    }

    /**
     * ?????? Wrapper ???????????????????????????
     *
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     * @param mapper ????????????
     */
    public <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return getBaseMapper().selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * ????????????
     *
     * @param page ????????????
     * @param queryWrapper ??????????????????????????? {@link QueryWrapper}
     */
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectMapsPage(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return pageMaps(page, Wrappers.emptyWrapper());
    }

    /**
     * ??????????????????
     *
     * @param consumer consumer
     *
     * @since 3.3.0
     * @deprecated 3.3.1 ???????????????????????? {@link #executeBatch(Collection, int, BiConsumer)} }.
     */
    @Deprecated
    protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
    }

    /**
     * ??????????????????
     *
     * @param list ????????????
     * @param batchSize ????????????
     * @param consumer ????????????
     * @param <E> ??????
     *
     * @return ????????????
     *
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    /**
     * ?????????????????????????????????????????????{@link com.baomidou.mybatisplus.extension.service.IService#DEFAULT_BATCH_SIZE}???
     *
     * @param list ????????????
     * @param consumer ????????????
     * @param <E> ??????
     *
     * @return ????????????
     *
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return executeBatch(list, DEFAULT_BATCH_SIZE, consumer);
    }

}
