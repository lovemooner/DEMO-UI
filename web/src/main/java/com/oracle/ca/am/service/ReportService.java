package com.oracle.ca.am.service;


import com.oracle.ca.am.model.PBC;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
public interface ReportService {

    List<String> getAllWorkSpaces();

    List<PBC> getPBC(String workspace,String project,String errorFile,int start, int limit);

    int countPBC(String workspace,String project,String errorFile);

    List<String> getProjects(String workspace);

    List<String> getErrorFiles(String workspace,String project, int start, int limit);
}
