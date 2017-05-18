package com.oracle.ca.am.controller;

import com.oracle.ca.am.model.PBC;
import com.oracle.ca.am.service.ReportService;
import love.moon.common.AllListResult;
import love.moon.common.Pager;
import love.moon.common.PagingListResult;
import love.moon.common.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nadong on 2017/4/20.
 */
@Controller
@RequestMapping("/")
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private final int START = 0;
    private final int LIMIT = 10;

    @Autowired
    private ReportService reportService;

    @RequestMapping("")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("workspaces", reportService.getAllWorkSpaces());
        return "index";

    }

    @ResponseBody
    @RequestMapping(value = "pbc", method = RequestMethod.GET)
    public Result getPBC(
            @RequestParam(value = "workspace", required = false) String workspace,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "errorfile", required = false) String errorfile,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {

        LOG.info("start:{},project:{}",start,project);
        start = start == null ? START : start;
        limit = limit == null ? LIMIT : limit;
        List<PBC> pbcs= reportService.getPBC(workspace,project,errorfile,start, limit);
        int totalRows=reportService.countPBC(workspace,project,errorfile);
        Pager pager=new Pager();
        pager.setStart(start);
        pager.setLimit(limit);
        pager.setTotalRows(totalRows);
        PagingListResult<PBC> listResult=new PagingListResult<PBC>(pbcs,pager);
;      return  listResult;
    }

    @ResponseBody
    @RequestMapping(value = "/pbc/workspace/{workspace}/project/{project}/errorfile/{errorfile}", method = RequestMethod.GET)
    public List<String> getProjects(@PathVariable("workspace") String workspace,
                                    @PathVariable("project") String project,
                                    @PathVariable("errorfile") String errorfile,
                                    @RequestParam("btnType") int start,
                                    @RequestParam("btnType") int limit
    ) {

        return reportService.getProjects(workspace);
    }

    @ResponseBody
    @RequestMapping(value = "/pbc/project", method = RequestMethod.GET)
    public List<String> getProjects(@RequestParam("workspace") String workspace) {
        return reportService.getProjects(workspace);
    }

    @ResponseBody
    @RequestMapping(value = "/pbc/errorcode", method = RequestMethod.GET)
    public List<String> getErrorFiles(@RequestParam("workspace") String workspace,
                                      @RequestParam("project") String project,
                                      @RequestParam(value = "start", required = false) Integer start,
                                      @RequestParam(value = "limit", required = false) Integer limit
    ) {
        start = start == null ? START : start;
        limit = limit == null ? 100 : limit;
        return reportService.getErrorFiles(workspace, project, start, limit);
    }

}
