/**
*      获取一个区域内的表单数据，转换为对象数组
*   参数p为一个对象，属性如下
*              target : 区域的ID
*              lineMode : 识别一行的CSS选择器
*              filterName : 判断是否过滤的元素name （如果设置了filter属性，则过滤） 
*              startRowNum : 从第几行开始获取数据，从0开始计数 
*              name : 选出name为这个的表单元素
*/
function generateRequestData(p){
        var l = [];
        var rownum = 0;
        var target = jQuery(p.target);
        if(p.nth)
                target = target.eq(p.nth);
        target.find(p.lineMode||"tr").each( function(i){
                if( i >= ( p.startRowNum||0 ) && i <= ( p.endRowNum||99999 )){
                       if(p.filterName){
                             //判断是否过滤
                             if( jQuery(this).find("[name='"+p.filterName+"']").attr("filter") ){
                                     return;
                             }
                       }
                       var d = {};
                       var count=0;
                       jQuery(this).find(":input[type!='button']").each(function(){
                               if(jQuery(this).attr("name")){
                                     if( (p.name && jQuery(this).attr("name") == p.name) 
                                              || !p.name ) {
                                             d[jQuery(this).attr("name")] = (!!p.noId) ? jQuery(this).val() : jQuery(this).attr("auto_val")||jQuery(this).val();
                                             count++;
                                     }else{
                                             return;
                                     }
                               }
                       });
                       if(count==0) return;
                       if(p.extendObj) d = jQuery.extend(true, d, p.extendObj);
                       d.rownum = rownum+1;
                       l.push(d);
                       rownum++;
                }
        } );
        return l;
}


/**
*      获取一个区域内的表单数据，转换为KEY-VALUE数组
*   参数p为一个对象，属性如下
*              target : 区域的ID
*              filterName : （如果设置了filter属性，则过滤） 
*/
function generateRequestKVData(p) {
        var l = [];
        jQuery(p.target).find(":input[type!='button']").each(function() {
                if(p.filterName) {
                       //判断是否过滤
                       if(jQuery(this).attr(p.filterName)) {
                               return;
                       }
                }
                var d = {};
                if(jQuery(this).attr("name")) {
                       d.name = jQuery(this).attr("name");
                       d.value = (!!p.noId) ? jQuery(this).val() : jQuery(this).attr("auto_val") || jQuery(this).val();
                       if(p.extendObj){
                               d = jQuery.extend(true, d, p.extendObj);
                       }
                       l.push(d);
                }
        });
        if(p.pushObj){
                l.push(jQuery.extend(true, p.pushObj, p.extendObj))
        }
        return l;
}


/**
* 获取一个目标内的表单元素转化为对象（横向）
* @param  {[type]} p [description]
* @return {[type]}   [description]
*/
function generateRequestMap(p){
        var m = {};
        var target = jQuery(p.target);
        if(p.nth)
                target = target.eq(p.nth);
        target.find(":input[type!='button']").each(function(){
                if(jQuery(this).attr("name")){
                       if(jQuery(this).attr("type")=="radio" ){
                             if(jQuery(this).attr("checked"))
                                     m[jQuery(this).attr("name")] = jQuery(this).val();
                       }else{
                             m[jQuery(this).attr("name")] = jQuery(this).attr("auto_val")||jQuery(this).val();
                       }
                       
                 }
        });
        if(p.extendObj) m = jQuery.extend(true, m, p.extendObj);  
        return m;
}
