package com.company.model.dao.constants;

public enum Parameter {
    QUERY, AUTHOR, GENRE;

    private String argument;
    private String query;

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
