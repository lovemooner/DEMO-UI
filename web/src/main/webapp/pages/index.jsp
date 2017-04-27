<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>CA_FMW_12C_PBC</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"/>
<link type="text/css" rel="stylesheet" href="styles/page.css"/>
<script type="text/javascript" src="js/lib/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body class="bodyMain">
<div>
<div>
  <div class="s-list">
     WORKSPACE &nbsp;&nbsp;
       <span>
           <select id="j_workspace_select">
               <option value="">ALL</option>
              <c:forEach items="${workspaces}" var="workspace" varStatus="status">
                  <c:if test="${workspace != null}">
                      <option value="${workspace}">${workspace}</option>
                  </c:if>
              </c:forEach>
           </select>
       </span>
        &nbsp;&nbsp;PROJECT&nbsp;&nbsp;
            <span>
                <select id="j_project_select">
                    <option value="">ALL</option>
                </select>
            </span>
  </div>
  <div class="s-list s-list-project">
       ERROR_FILE &nbsp;&nbsp;
             <span>
                 <select  id="j_error_file_select">
                     <option value="">ALL</option>
                 </select>
             </span>
  </div>
</div>
<div class="s-detail-hr"></div>
<div class="s-detail">
  <div></div>
  <table id="pbcTable" cellpadding="0" cellspacing="0" class="s-table">
          <colgroup>
          <col width="8%">
          <col width="10%">
          <col>
          <col width="3%">
          <col width="10%">
          </colgroup>
          <thead>
          <tr class="tabTitle">
              <td>VALIDATION_DATE</td>
              <td>WORKSPACE&PROJECT</td>
              <td>ERROR_FILE</td>
              <td>COORD</td>
              <td>ERROR_TEXT</td>
          </tr>
          </thead>
          <tbody id="pbc_tbody"></tbody>
      </table>
</div>
  <div class="simplePage">
     <jsp:include page="/paging.jsp">
         <jsp:param name="dynamicalID" value="dynamical1"></jsp:param>
     </jsp:include>
   </div>
<div class="i-height"></div>

</div>
</body>
</html>
