package com.avyay.json;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Avyay Natarajan
 * @version 0.1.0
 */
public final class JSON {
    public static enum Type
    {
        Number, String, Object, Array, Boolean, Null;
    }

    public static abstract class Value
    {
        public final Type Type;

        public Value(Type Type)
        {
            this.Type = Type;
        }

        public boolean isNumber()
        {
            return Type == JSON.Type.Number;
        }

        public boolean isBoolean()
        {
            return Type == JSON.Type.Boolean;
        }

        public boolean isString()
        {
            return Type == JSON.Type.String;
        }

        public boolean isObject()
        {
            return Type == JSON.Type.Object;
        }

        public boolean isArray()
        {
            return Type == JSON.Type.Array;
        }

        public boolean isNull()
        {
            return Type == JSON.Type.Null;
        }

        public Float asFloat()
        {
            if (isNumber())
                return (Float) this;
            throw new ClassCastException();
        }

        public Int asInt()
        {
            if (isNumber())
                return (Int) this;
            throw new ClassCastException();
        }

        public Bool asBoolean()
        {
            if (isBoolean())
                return (Bool) this;
            throw new ClassCastException();
        }

        public String asString()
        {
            if (isString())
                return (String) this;
            throw new ClassCastException();
        }

        public Object asObject()
        {
            if (isObject())
                return (Object) this;
            throw new ClassCastException();
        }

        public Array asArray()
        {
            if (isArray())
                return (Array) this;
            throw new ClassCastException();
        }

        public abstract java.lang.String toString();
    }

    public static final class Int extends Value
    {
        private final int _val;

        public Int(int value)
        {
            super(JSON.Type.Number);
            _val = value;
        }

        public int value()
        {
            return _val;
        }

        public java.lang.String toString()
        {
            return "" + _val;
        }
    }

    public static final class Float extends Value
    {
        private final float _val;

        public Float(float value)
        {
            super(JSON.Type.Number);
            _val = value;
        }

        public float value()
        {
            return _val;
        }

        public java.lang.String toString()
        {
            return "" + _val;
        }
    }

    public static final class Bool extends Value
    {
        private final boolean _val;

        public Bool(boolean value)
        {
            super(JSON.Type.Boolean);
            _val = value;
        }

        public boolean value()
        {
            return _val;
        }

        public java.lang.String toString()
        {
            return _val ? "true" : "false";
        }
    }

    public static final class String extends Value
    {
        private final java.lang.String _val;

        public String(java.lang.String value)
        {
            super(JSON.Type.String);
            _val = value;
        }

        public java.lang.String value()
        {
            return _val;
        }

        public java.lang.String toString()
        {
            return "\"" + _val + "\"";
        }
    }

    public static final class Null extends Value
    {
        public Null()
        {
            super(JSON.Type.Null);
        }

        public Null value()
        {
            return null;
        }

        public java.lang.String toString()
        {
            return "null";
        }
    }

    public static final class Object extends Value implements Map<java.lang.String, Value>
    {
        private final Map<java.lang.String, Value> _val = new HashMap<>();

        public Object()
        {
            super(JSON.Type.Object);
        }

        @Override
        public int size()
        {
            return _val.size();
        }

        @Override
        public boolean isEmpty() {
            return _val.isEmpty();
        }

        @Override
        public boolean containsKey(@Nullable java.lang.Object key) {
            return _val.containsKey(key);
        }

        @Override
        public boolean containsValue(@Nullable java.lang.Object value) {
            return _val.containsValue(value);
        }

        @Nullable
        @Override
        public Value get(@Nullable java.lang.Object key) {
            return _val.get(key);
        }

        @Nullable
        @Override
        public Value put(java.lang.String key, Value value) {
            return _val.put(key, value);
        }

        @Nullable
        @Override
        public Value remove(@Nullable java.lang.Object key) {
            return _val.remove(key);
        }

        @Override
        public void putAll(@NonNull Map<? extends java.lang.String, ? extends Value> m) {
            _val.putAll(m);
        }

        @Override
        public void clear() {
            _val.clear();
        }

        @NonNull
        @Override
        public Set<java.lang.String> keySet() {
            return _val.keySet();
        }

        @NonNull
        @Override
        public Collection<Value> values() {
            return _val.values();
        }

        @NonNull
        @Override
        public Set<Entry<java.lang.String, Value>> entrySet() {
            return _val.entrySet();
        }

        @Nullable
        @Override
        public Value getOrDefault(@Nullable java.lang.Object key, @Nullable Value defaultValue) {
            return _val.getOrDefault(key, defaultValue);
        }

        @Override
        public void forEach(@NonNull BiConsumer<? super java.lang.String, ? super Value> action) {
            _val.forEach(action);
        }

        @Override
        public void replaceAll(@NonNull BiFunction<? super java.lang.String, ? super Value, ? extends Value> function) {
            _val.replaceAll(function);
        }

        @Nullable
        @Override
        public Value putIfAbsent(java.lang.String key, Value value) {
            return _val.putIfAbsent(key, value);
        }

        @Override
        public boolean remove(@Nullable java.lang.Object key, @Nullable java.lang.Object value) {
            return _val.remove(key, value);
        }

        @Override
        public boolean replace(java.lang.String key, @Nullable Value oldValue, Value newValue) {
            return _val.replace(key, oldValue, newValue);
        }

        @Nullable
        @Override
        public Value replace(java.lang.String key, Value value) {
            return _val.replace(key, value);
        }

        @Nullable
        @Override
        public Value computeIfAbsent(java.lang.String key, @NonNull Function<? super java.lang.String, ? extends Value> mappingFunction) {
            return _val.computeIfAbsent(key, mappingFunction);
        }

        @Nullable
        @Override
        public Value computeIfPresent(java.lang.String key, @NonNull BiFunction<? super java.lang.String, ? super Value, ? extends Value> remappingFunction) {
            return _val.computeIfPresent(key, remappingFunction);
        }

        @Nullable
        @Override
        public Value compute(java.lang.String key, @NonNull BiFunction<? super java.lang.String, ? super Value, ? extends Value> remappingFunction) {
            return _val.compute(key, remappingFunction);
        }

        @Nullable
        @Override
        public Value merge(java.lang.String key, @NonNull Value value, @NonNull BiFunction<? super Value, ? super Value, ? extends Value> remappingFunction) {
            return _val.merge(key, value, remappingFunction);
        }

        public java.lang.String toString()
        {
            java.lang.String out = "{";
            Iterator<java.lang.String> keys = keySet().iterator();
            while (keys.hasNext()) {
                java.lang.String key = keys.next();
                out += "\"" + key + "\":" + get(key).toString();
                if (keys.hasNext()) out += ",";
            }
            out += "}";
            return out;
        }
    }

    public static final class Array extends Value implements List<Value>, Iterable<Value>
    {
        private final List<Value> _val;

        public Array()
        {
            super(JSON.Type.Array);
            _val = new ArrayList<>();
        }

        public Array(int length)
        {
            super(JSON.Type.Array);
            _val = new ArrayList<>(length);
        }

        public int size() {
            return _val.size();
        }

        public boolean isEmpty() {
            return _val.isEmpty();
        }

        @NonNull
        @Override
        public Iterator<Value> iterator() {
            return _val.iterator();
        }

        @NonNull
        public Value[] toArray() {
            Value[] out = new Value[_val.size()];
            for (int i = 0; i<out.length; i++) out[i] = _val.get(i);
            return out;
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        public boolean add(Value value) {
            return _val.add(value);
        }

        public boolean remove(@Nullable java.lang.Object o) {
            return _val.remove(o);
        }

        @Override
        public boolean contains(@Nullable java.lang.Object o) {
            return _val.contains(o);
        }

        public boolean containsAll(@NonNull Collection<?> c) {
            return _val.containsAll(c);
        }

        public boolean addAll(@NonNull Collection<? extends Value> c) {
            return _val.addAll(c);
        }

        public boolean addAll(int index, @NonNull Collection<? extends Value> c) {
            return _val.addAll(index, c);
        }

        public boolean removeAll(@NonNull Collection<?> c) {
            return _val.removeAll(c);
        }

        public boolean retainAll(@NonNull Collection<?> c) {
            return _val.retainAll(c);
        }

        public void clear() {
            _val.clear();
        }

        public Value get(int index) {
            return _val.get(index);
        }

        public Value set(int index, Value element) {
            return _val.set(index, element);
        }

        public void add(int index, Value element) {
            _val.add(index, element);
        }

        public Value remove(int index) {
            return _val.remove(index);
        }

        @Override
        public int indexOf(@Nullable java.lang.Object o) {
            return _val.indexOf(o);
        }

        @Override
        public int lastIndexOf(@Nullable java.lang.Object o) {
            return _val.lastIndexOf(o);
        }

        @NonNull
        @Override
        public ListIterator<Value> listIterator() {
            return _val.listIterator();
        }

        @NonNull
        @Override
        public ListIterator<Value> listIterator(int index) {
            return _val.listIterator(index);
        }

        @NonNull
        @Override
        public List<Value> subList(int fromIndex, int toIndex) {
            return _val.subList(fromIndex, toIndex);
        }

        @Override
        public java.lang.String toString()
        {
            java.lang.String out = "[";
            for (int i=0;i<size();i++)
            {
                out += get(i).toString();
                if (i < size() - 1)
                    out += ",";
            }
            out += "]";
            return out;
        }
    }

    public static JSON.Value parse(java.lang.String in)
    {
        return Parser.parseValue(in);
    }

    public static java.lang.String stringify(JSON.Value val)
    {
        return val.toString();
    }
}
