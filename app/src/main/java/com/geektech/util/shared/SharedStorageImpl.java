package com.geektech.util.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedStorageImpl implements SharedStorage {

    private SharedPreferences sharedPreferences;

    public SharedStorageImpl(Context context, String fileName) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(
                    fileName,
                    Context.MODE_PRIVATE
                );
    }

    //region Private

    private void setLong(String key, long value) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    private void setFloat(String key, float value) {
        sharedPreferences.edit()
                .putFloat(key, value)
                .apply();
    }

    private void setInt(String key, int value) {
        sharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    private void setString(String key, String value) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    private void setBoolean(String key, boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    //endregion

    //region Contract

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, T defValue) {
        if (defValue instanceof Integer) {
            return (T) Integer.valueOf(sharedPreferences.getInt(key, (Integer) defValue));
        } else if (defValue instanceof Boolean) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, (Boolean) defValue));
        } else if (defValue instanceof String) {
            return (T) String.valueOf(sharedPreferences.getString(key, (String) defValue));
        } else if (defValue instanceof Float) {
            return (T) Float.valueOf(sharedPreferences.getFloat(key, (Float) defValue));
        } else if (defValue instanceof Long) {
            return (T) Long.valueOf(sharedPreferences.getLong(key, (Long) defValue));
        }

        return defValue;
    }

    @Override
    public <T> void set(String key, T value) {
        if (value instanceof Integer) {
            setInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            setBoolean(key, (Boolean) value);
        } else if (value instanceof String){
            setString(key, (String) value);
        } else if (value instanceof Float) {
            setFloat(key, (Float) value);
        } else if (value instanceof Long) {
            setLong(key, (Long) value);
        }
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void delete(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    //endregion
}
