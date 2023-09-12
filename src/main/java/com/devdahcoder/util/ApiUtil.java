package com.devdahcoder.util;

public abstract class ApiUtil {

    public static int calculatePagination(int size, int limit) {

        return (int) Math.ceil((double) size / limit);

    }

    public static boolean hasPreviousPage(int page) {

        int validatePage = page <= 0 ? 1 : page;

        return validatePage > 1;

    }

    public static boolean hasNextPage(int page, int totalPage) {

        return page < totalPage ;

    }

}
