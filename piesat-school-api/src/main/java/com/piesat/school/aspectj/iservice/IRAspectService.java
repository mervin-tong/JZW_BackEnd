package com.piesat.school.aspectj.iservice;

import java.util.Date;

public interface IRAspectService {
    Boolean addRecord(String methodName, StringBuilder params, String username, Date operationTime);
}
