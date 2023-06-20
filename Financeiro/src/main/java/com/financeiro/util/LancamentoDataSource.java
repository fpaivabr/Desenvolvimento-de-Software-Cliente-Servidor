package com.financeiro.util;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LancamentoDataSource implements JRDataSource {
    private List<Map<String, Object>> data;
    private Iterator<Map<String, Object>> iterator;
    private Map<String, Object> currentObject;

    public LancamentoDataSource(List<Map<String, Object>> data) {
        this.data = data;
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
