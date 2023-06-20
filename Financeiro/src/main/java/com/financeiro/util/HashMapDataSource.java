package com.financeiro.util;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HashMapDataSource implements JRDataSource {
    private List<Map<String, Object>> data;
    private Iterator<Map<String, Object>> iterator;
    private Map<String, Object> currentObject;

    public HashMapDataSource(HashMap<String, Object> hashMap) {
        this.data = new ArrayList<>();
        this.data.add(hashMap);
        this.iterator = data.iterator();
    }

    @Override
    public boolean next() throws JRException {
        if (iterator.hasNext()) {
            currentObject = iterator.next();
            return true;
        }
        return false;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        if (currentObject.containsKey(fieldName)) {
            return currentObject.get(fieldName);
        }
        return null;
    }
}