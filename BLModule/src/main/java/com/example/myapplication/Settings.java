package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.xhb.prism.prop.PropKey;
import com.xhb.prism.prop.PropMutableKey;
import com.xhb.prism.prop.PropertySet;

class Settings extends PropertySet {

    private SharedPreferences sp;

    public Settings(Context context, String store) {
        sp = context.getSharedPreferences(store, Context.MODE_PRIVATE);
    }

    @Override
    public <E> E getProp(PropKey<E> key, E dflt) {
        if (key.getType() == Integer.class)
            return (E) Integer.valueOf(sp.getInt(key.getName(), (Integer) dflt));
        else if (key.getType() == Boolean.class)
            return (E) Boolean.valueOf(sp.getBoolean(key.getName(), (Boolean) dflt));
        else if (key.getType() == Long.class)
            return (E) Long.valueOf(sp.getLong(key.getName(), (Long) dflt));
        else if (key.getType() == Float.class)
            return (E) Float.valueOf(sp.getFloat(key.getName(), (Float) dflt));
        else if (key.getType() == String.class)
            return (E) sp.getString(key.getName(), (String) dflt);
        else
            return (E) sp.getAll().get(key.getName());
    }

    @Override
    public <E> void setProp(PropMutableKey<E> key, E value) {
        if (value == null)
            sp.edit().remove(key.getName()).apply();
        else if (key.getType() == Integer.class)
            sp.edit().putInt(key.getName(), (Integer) value).apply();
        else if (key.getType() == Boolean.class)
            sp.edit().putBoolean(key.getName(), (Boolean) value).apply();
        else if (key.getType() == Long.class)
            sp.edit().putLong(key.getName(), (Long) value).apply();
        else if (key.getType() == Float.class)
            sp.edit().putFloat(key.getName(), (Float) value).apply();
        else if (key.getType() == String.class)
            sp.edit().putString(key.getName(), (String) value).apply();
    }
}
