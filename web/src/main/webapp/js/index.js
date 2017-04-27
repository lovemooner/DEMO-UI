;
$(function () {
    pager.init({
      "fn":function(currentPage){
                var workspace=$("#j_workspace_select option:selected").val();
                var project=$("#j_project_select option:selected").val();
                var errorfile=$("#j_error_file_select option:selected").val();
                ;
                 var url="/web/pbc?workspace="+workspace+"&project="+project+"&errorfile="+errorfile+"&start="+(currentPage-1);
                 $.ajax({
                       type: "GET",
                       url:url,
                       dataType: "json",
                       async: false,
                       success: function (data) {
                            initTable(data);
                            var totalRows=data.pager.totalRows;
                            pager.refreshUl(totalRows,currentPage);
                       }
                 });
           }
    });
   $("#j_workspace_select").change(function(){
      var workspace=$(this).find("option:selected").val();
      $("#j_error_file_select").val("")
         .find("option:gt(0)").remove();
      $("#j_project_select").val("")
          .find("option:gt(0)").remove();
      pager.refresh();
      $.ajax({
               type: "GET",
               url:"/web/pbc/project?workspace="+workspace ,
               dataType: "json",
               async: false,
               success: function (data) {
                     var item="";
                     for(var i in data){
                        item+='<option value="'+data[i]+'">'+data[i]+'</option>';
                     }
                     $("#j_project_select option:gt(0)").remove();
                    $("#j_project_select").append(item);
               }
           });

   });


  $("#j_project_select").change(function(){
       $("#j_error_file_select").val("");
        var workspace=$("#j_workspace_select option:selected").val();
        var project=$(this).find("option:selected").val();
        pager.refresh();
        var url="/web/pbc/errorcode?workspace="+workspace+"&project="+project
        $.ajax({
                 type: "GET",
                 url:url,
                 dataType: "json",
                 async: false,
                 success: function (data) {
                       var item="";
                       for(var i in data){
                          item+='<option value="'+data[i]+'">'+data[i]+'</option>';
                       }
                       $("#j_error_file_select option:gt(0)").remove();
                      $("#j_error_file_select").append(item);
                 }
             });
     });

    $("#j_error_file_select").change(function(){
         pager.refresh();
    });

});

    function initTable(data){
       var results=data.results;
          var item="";
         for(var i in results){
            item+='<tr>'+
                     '<td>'+results[i].validationDate+'</td>'+
                     '<td>'+results[i].workspace+'<br>/'+results[i].project+'</td>'+
                     '<td>'+results[i].errorFile+'</td>'+
                     '<td>'+results[i].coord+'</td>'+
                     '<td>'+results[i].errorText+'</td>'+
                 '</tr>';
         }
         $("#pbc_tbody").html(item);
       }