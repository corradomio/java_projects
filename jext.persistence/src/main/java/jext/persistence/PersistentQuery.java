package jext.persistence;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Stream;

public class PersistentQuery<T> implements TypedQuery<T> {

    private final TypedQuery<T> tq;

    PersistentQuery(TypedQuery<T> tq) {
        this.tq = tq;
    }

    // ----------------------------------------------------------------------

    public TypedQuery<T> query() {
        return this.tq;
    }

    // ----------------------------------------------------------------------

    @Override
    public List<T> getResultList() {
        return tq.getResultList();
    }

    @Override
    public Stream<T> getResultStream() {
        return tq.getResultStream();
    }

    @Override
    public T getSingleResult() {
        return tq.getSingleResult();
    }

    @Override
    public TypedQuery<T> setMaxResults(int maxResult) {
        return tq.setMaxResults(maxResult);
    }

    @Override
    public TypedQuery<T> setFirstResult(int startPosition) {
        return tq.setFirstResult(startPosition);
    }

    @Override
    public TypedQuery<T> setHint(String hintName, Object value) {
        return tq.setHint(hintName, value);
    }

    @Override
    public <T1> TypedQuery<T> setParameter(Parameter<T1> param, T1 value) {
        return tq.setParameter(param, value);
    }

    @Override
    public TypedQuery<T> setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
        return tq.setParameter(param, value, temporalType);
    }

    @Override
    public TypedQuery<T> setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
        return tq.setParameter(param, value, temporalType);
    }

    @Override
    public TypedQuery<T> setParameter(String name, Object value) {
        return tq.setParameter(name, value);
    }

    @Override
    public TypedQuery<T> setParameter(String name, Calendar value, TemporalType temporalType) {
        return tq.setParameter(name, value, temporalType);
    }

    @Override
    public TypedQuery<T> setParameter(String name, Date value, TemporalType temporalType) {
        return tq.setParameter(name, value, temporalType);
    }

    @Override
    public TypedQuery<T> setParameter(int position, Object value) {
        return tq.setParameter(position, value);
    }

    @Override
    public TypedQuery<T> setParameter(int position, Calendar value, TemporalType temporalType) {
        return tq.setParameter(position, value, temporalType);
    }

    @Override
    public TypedQuery<T> setParameter(int position, Date value, TemporalType temporalType) {
        return tq.setParameter(position, value, temporalType);
    }

    @Override
    public TypedQuery<T> setFlushMode(FlushModeType flushMode) {
        return tq.setFlushMode(flushMode);
    }

    @Override
    public TypedQuery<T> setLockMode(LockModeType lockMode) {
        return tq.setLockMode(lockMode);
    }

    @Override
    public int executeUpdate() {
        return tq.executeUpdate();
    }

    @Override
    public int getMaxResults() {
        return tq.getMaxResults();
    }

    @Override
    public int getFirstResult() {
        return tq.getFirstResult();
    }

    @Override
    public Map<String, Object> getHints() {
        return tq.getHints();
    }

    @Override
    public Set<Parameter<?>> getParameters() {
        return tq.getParameters();
    }

    @Override
    public Parameter<?> getParameter(String name) {
        return tq.getParameter(name);
    }

    @Override
    public <T> Parameter<T> getParameter(String name, Class<T> type) {
        return tq.getParameter(name, type);
    }

    @Override
    public Parameter<?> getParameter(int position) {
        return tq.getParameter(position);
    }

    @Override
    public <T> Parameter<T> getParameter(int position, Class<T> type) {
        return tq.getParameter(position, type);
    }

    @Override
    public boolean isBound(Parameter<?> param) {
        return tq.isBound(param);
    }

    @Override
    public <T> T getParameterValue(Parameter<T> param) {
        return tq.getParameterValue(param);
    }

    @Override
    public Object getParameterValue(String name) {
        return tq.getParameterValue(name);
    }

    @Override
    public Object getParameterValue(int position) {
        return tq.getParameterValue(position);
    }

    @Override
    public FlushModeType getFlushMode() {
        return tq.getFlushMode();
    }

    @Override
    public LockModeType getLockMode() {
        return tq.getLockMode();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return tq.unwrap(cls);
    }
}
