<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
var  pager = {
    "pageId":"",
    "maxshowpageitem":5,//最多显示的页码个数
    "limit":10,//每一页显示的内容条数
    "initTable":"",
    "url":"",
    "fn":"",
    /**
     * 初始化分页界面
     * @param totalRows 数据总量
     */
    "refreshUl":function(totalRows,currentPage){
        currentPage=parseInt(currentPage);
        var pageCount = 1;
        if(totalRows>=0){
            pageCount = totalRows%pager.limit>0?(parseInt(totalRows/pager.limit)+1):totalRows/pager.limit;
        }
        var appendStr = pager.getPageListModel(pageCount,currentPage);
        $("#"+pager.pageId).html(appendStr);
    },
    "init":function(data){
          pager.fn=data.fn;
          var limit=data.limit;
          if(parseInt(limit)>0){
            pager.limit=limit;
          }
          pager.pageId=$("#pager").attr("id");
          pager.fn(1)
          pager.itemEvent(pager.fn);
    },
    "refresh":function(){
       pager.fn(1);
    },
    "itemEvent":function(fn){
          $("#"+pager.pageId +">li[class='pageItem']").live("click",function(){
              fn($(this).attr("page-data"));
          });
    },
    "getPageListModel":function(pageCount,currentPage){
        currentPage=parseInt(currentPage);
        var prePage = currentPage-1;
        var nextPage = currentPage+1;
        var prePageClass ="pageItem";
        var nextPageClass = "pageItem";
        if(prePage<=0){
            prePageClass="pageItemDisable";
        }
        if(nextPage>pageCount){
            nextPageClass="pageItemDisable";
        }
        var appendStr ="";
        appendStr+="<li class='"+prePageClass+"' page-data='1' page-rel='firstpage'>首页</li>";
        appendStr+="<li class='"+prePageClass+"' page-data='"+prePage+"' page-rel='prepage'>&lt;上一页</li>";
        var miniPageNumber = 1;
        if(currentPage-parseInt(pager.maxshowpageitem/2)>0&&currentPage+parseInt(pager.maxshowpageitem/2)<=pageCount){
            miniPageNumber = currentPage-parseInt(pager.maxshowpageitem/2);
        }else if(currentPage-parseInt(pager.maxshowpageitem/2)>0&&currentPage+parseInt(pager.maxshowpageitem/2)>pageCount){
            miniPageNumber = pageCount-pager.maxshowpageitem+1;
            if(miniPageNumber<=0){
                miniPageNumber=1;
            }
        }
        var showPageNum = parseInt(pager.maxshowpageitem);
        if(pageCount<showPageNum){
            showPageNum = pageCount
        }
        for(var i=0;i<showPageNum;i++){
            var pageNumber = miniPageNumber++;
            var itemPageClass = "pageItem";
            if(pageNumber==currentPage){
                itemPageClass = "pageItemActive";
            }

            appendStr+="<li class='"+itemPageClass+"' page-data='"+pageNumber+"' page-rel='itempage'>"+pageNumber+"</li>";
        }
        appendStr+="<li class='"+nextPageClass+"' page-data='"+nextPage+"' page-rel='nextpage'>下一页&gt;</li>";
        appendStr+="<li class='"+nextPageClass+"' page-data='"+pageCount+"' page-rel='lastpage'>尾页</li>";
       return appendStr;

    }
}

$(function () {
   var data='<%=request.getParameter("dynamicalID")%>';
});

</script>

<ul class="page"  id="pager"></ul>