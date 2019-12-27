/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */ ;
layui.define(["table", "form", "jquery"], function (e) {
    var t = layui.$,
        i = layui.table;
    var $ = layui.jquery;
    var form = layui.form;
    var history_customer_id;
    
    i.render({
        elem: "#LAY-user-manage",
        url: "http://localhost:8080/boot-crm/user_manage/users",
        method: "get",
        parseData: function(res){ //res 即为原始返回的数据
            return {
              "code": res.status, //解析接口状态
              "msg": res.msg, //解析提示文本
              "count": res.data.total, //解析数据长度
              "data": res.data.list //解析数据列表
            };
        },
        cols: [
            [{
                type: "checkbox",
                align: "center",
                fixed: "left"
            }, {
                field: "id",
                width: 150,
                title: "ID",
                align: "center"
            }, {
                field: "logincode",
                title: "登录名",
                align: "center",
                minWidth: 150
            }, {
                field: "username",
                title: "用户名",
                align: "center",
                width: 200
            }, {
                field: "type",
                title: "用户类型",
                align: "center",
                templet:"#table-useradmin-type"
            }, {
                field: "status",
                title: "账号状态",
                align: "center",
                templet:"#table-useradmin-status"
            }, {
                title: "操作",
                width: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-useradmin-webuser"
            }]
        ],
        page: !0,
        limit: 10,
        height: "full-220",
        text: { //自定义文本，此处用法--》当返回数据为空时的异常提示
        	none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }
    }), i.on("tool(LAY-user-manage)", function (e) {
        e.data;
        if ("del" === e.event) layer.prompt({
            formType: 1,
            title: "敏感操作，请验证口令"
        }, function (t, i) {
            layer.close(i), layer.confirm("真的删除行么", function (t) {
            	var id = e.data.id;
            	$.ajax({
              	  	url:'http://localhost:8080/boot-crm/user_manage/user/'+id,
                    dataType:'json',
                    type:'delete',
                    success: function (data) {
                          if(data.status==0){
        	                  //请求成功
                        	  layer.msg(data.msg);
                          }else{
                        	  layer.msg(data.msg,{
                        		  offset: '15px'
                        	      ,icon: 2
                        	      ,time: 3000
                        	  })
                          }
                    },
                    error: function(data) {
                    	layer.msg('出错了！',{
                  		  offset: '15px'
                  	      ,icon: 2
                  	      ,time: 3000
                  	  })
                    }
                });
                e.del(), layer.close(t)
            })
        });
        else if ("edit" === e.event) {
            t(e.tr);
            // 旧数据
            var oldData = e.data;
            layer.open({
                type: 2,
                title: "编辑用户",
                content: "/boot-crm/user_manage/userform_page",
                maxmin: !0,
                area: ['460px', '390px'],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                	var l = window["layui-layer-iframe" + e],
                        r = "LAY-user-front-submit",
                        n = t.find("iframe").contents().find("#" + r);
                    	
                    l.layui.form.on("submit(" + r + ")", function (t) {
                    	var field = t.field;
                        //ajax
                        $.ajax({
                      	  	url:'http://localhost:8080/boot-crm/user_manage/user',
                            data:field,
                            dataType:'json',
                            type:'put',
                            success: function (data) {
                                  if(data.status==0){
                	                  //请求成功
                                	  layer.msg(data.msg);
                	                  //刷新表格数据
                                    i.reload('LAY-user-manage');
                                    i.reload("LAY-user-front-submit");
                                    layer.close(e);
                                  }else{
                                	  layer.msg(data.msg,{
                                		  offset: '15px'
                                	      ,icon: 2
                                	      ,time: 3000
                                	  })
                                  }
                            },
                            error: function(data) {
                            	layer.msg('出错了！',{
                          		  offset: '15px'
                          	      ,icon: 2
                          	      ,time: 3000
                          	  })
                            }
                        });
                    }), n.trigger("click")
                },
                success: function (e, t) {
                	var body = layer.getChildFrame('body', t);
                	// 数据回显
                    body.find('#id').val(oldData.id);
                    body.find('#logincode').val(oldData.logincode);
                    body.find('#logincode').attr('disabled','disabled');
                    body.find('#username').val(oldData.username);
                    body.find('#password').val(oldData.password);
                    body.find('#type').val(oldData.type);
                    body.find('#status').val(oldData.status);
                }
            })
        }
    }), i.render({
        elem: "#LAY-user-back-manage",
        url: "http://localhost:8080/boot-crm/customer_manage/customers",
        method: "get",
        parseData: function(res){ //res 即为原始返回的数据
            return {
              "code": res.status, //解析接口状态
              "msg": res.msg, //解析提示文本
              "count": res.data.total, //解析数据长度
              "data": res.data.list //解析数据列表
            };
        },
        cols: [
            [{
                type: "checkbox",
                align: "center",
                fixed: "left"
            }, {
                field: "id",
                width: 60,
                align: "center",
                title: "ID"
            }, {
                field: "name",
            	width: 100,
                align: "center",
                title: "客户名"
            }, {
                field: "industry",
            	width: 150,
                align: "center",
                title: "所属专业"
            }, {
                field: "level",
                title: "客户级别",
                width: 100,
                align: "center",
                templet:"#table-customer-level"
            }, {
            	field: "phone",
                width: 100,
                align: "center",
                title: "固话"
            }, {
            	field: "mobile",
            	width: 120,
                align: "center",
                title: "手机号码"
            }, {
            	field: "zipcode",
            	width: 80,
                align: "center",
                title: "邮编"
            }, {
            	field: "address",
                align: "center",
                title: "地址"
            }, {
                field: "creator",
                width: 80,
                align: "center",
                title: "创建者"
            }, {
            	field: "createTime",
            	width: 180,
                align: "center",
                title: "创建时间"
            }, {
                title: "操作",
                width: 150,
                align: "center",
                fixed: "right",
                toolbar: "#table-useradmin-admin"
            }]
        ],
        page: !0,
        limit: 10,
        height: "full-220",
        text: { //自定义文本，此处用法--》当返回数据为空时的异常提示
        	none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }
    }), i.on("tool(LAY-user-back-manage)", function (e) {
        var oldData = e.data;
        if ("del" === e.event) layer.prompt({
            formType: 1,
            title: "敏感操作，请验证口令"
        }, function (t, i) {
            layer.close(i), layer.confirm("确定删除此客户？", function (t) {
            	var id = e.data.id;
            	$.ajax({
              	  	url:'http://localhost:8080/boot-crm/customer_manage/customer/'+id,
                    dataType:'json',
                    type:'delete',
                    success: function (data) {
                          if(data.status==0){
        	                  //请求成功
                        	  layer.msg(data.msg);
                          }else{
                        	  layer.msg(data.msg,{
                        		  offset: '15px'
                        	      ,icon: 2
                        	      ,time: 3000
                        	  })
                          }
                    },
                    error: function(data) {
                    	layer.msg('出错了！',{
                  		  offset: '15px'
                  	      ,icon: 2
                  	      ,time: 3000
                  	  })
                    }
                });
                e.del(), layer.close(t)
            })
        });
        else if ("edit" === e.event) {
            t(e.tr);
            layer.open({
                type: 2,
                title: "编辑客户",
                content: "/boot-crm/customer_manage/form_page",
                area: ["420px", "520px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e],
                        r = "LAY-user-back-submit",
                        n = t.find("iframe").contents().find("#" + r);
                    
                    l.layui.form.on("submit(" + r + ")", function (t) {
                        var field = t.field;
                        $.ajax({
                      	  	url:'http://localhost:8080/boot-crm/customer_manage/customer',
                            data:field,
                            dataType:'json',
                            type:'put',
                            success: function (data) {
                                  if(data.status==0){
                	                  //请求成功
                                	  layer.msg(data.msg);
                	                  //刷新表格数据
                                    i.reload('LAY-user-back-manage');
                                  }else{
                                	  layer.msg(data.msg,{
                                		  offset: '15px'
                                	      ,icon: 2
                                	      ,time: 3000
                                	  })
                                  }
                            },
                            error: function(data) {
                            	layer.msg('出错了！',{
                          		  offset: '15px'
                          	      ,icon: 2
                          	      ,time: 3000
                          	  })
                            }
                        });
                        i.reload("LAY-user-back-submit"), layer.close(e)
                    }), n.trigger("click")
                },
                success: function (e, t) {
                	var body = layer.getChildFrame('body', t);
                	// 数据回显
                    body.find('#id').val(oldData.id);
                    body.find('#name').val(oldData.name);
                    body.find('#industry').val(oldData.industry);
                    body.find('#level').val(oldData.level);
                    body.find('#phone').val(oldData.phone);
                    body.find('#mobile').val(oldData.mobile);
                    body.find('#zipcode').val(oldData.zipcode);
                    body.find('#address').val(oldData.address);
                }
            })
        }
    }), i.render({
        elem: "#LAY-order-manage",
        url: "http://localhost:8080/boot-crm/order_manage/orders",
        method: "get",
        parseData: function(res){ //res 即为原始返回的数据
            return {
              "code": res.status, //解析接口状态
              "msg": res.msg, //解析提示文本
              "count": res.data.total, //解析数据长度
              "data": res.data.list //解析数据列表
            };
        },
        cols: [
            [{
                field: "customerId",
                width: 80,
                align: "center",
                title: "客户ID"
            }, {
                field: "customerName",
                align: "center",
                title: "客户名"
            }, {
                field: "payType",
                title: "宽带类型",
                align: "center",
                templet:"#table-order-type"
                
            }, {
                field: "expiryTime",
                align: "center",
                title: "到期时间"
            }, {
                field: "remark",
                align: "center",
                title: "备注",
                templet:"#table-order-remark"
            }, {
                title: "操作",
                width: 220,
                align: "center",
                fixed: "right",
                toolbar: "#table-useradmin-admin"
            }]
        ],
        page: !0,
        limit: 10,
        height: "full-220",
        text: { //自定义文本，此处用法--》当返回数据为空时的异常提示
        	none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }
    }), i.on("tool(LAY-order-manage)", function (e) {
        var oldData = e.data;
        history_customer_id = oldData.customerId;
        if ("history_order" === e.event){
        	layer.open({
                type: 2,
                title: "["+oldData.customerName+"] 的历史订单",
                content: "/boot-crm/order_manage/history_order_page",
                area: ["840px", "400px"],
                
                success: function (e, t) {
                	var body = layer.getChildFrame('body', t);
                	var l = window["layui-layer-iframe" + t];
                	body.find('#customerId').val(oldData.customerId);
                	
                	$.ajax({
                    	  url:'http://localhost:8080/boot-crm/order_manage/order_sum/'+oldData.customerId,
                          dataType:'json',
                          type:'get',
                          success: function (data) {
                                if(data.status==0){
              	                  //请求成功
                                  body.find('#sumPayment').html(data.data.sumPayment);
                                }else{
                              	  layer.msg(data.msg,{
                              		  offset: '15px'
                              	      ,icon: 2
                              	      ,time: 3000
                              	  })
                                }
                          },
                          error: function(data) {
                          	layer.msg('出错了！',{
                        		  offset: '15px'
                        	      ,icon: 2
                        	      ,time: 3000
                        	  })
                          }
                      });
                }
            });
        }
        else if ("renew" === e.event) {
            t(e.tr);
            layer.open({
                type: 2,
                title: "续费宽带",
                content: "/boot-crm/order_manage/form_page",
                area: ["460px", "490px"],
                btn: ["确定", "取消"],
                yes: function (e, t) {
                    var l = window["layui-layer-iframe" + e],
                        r = t.find("iframe").contents().find("#LAY-order-submit");
                    l.layui.form.on("submit(LAY-order-submit)", function (t) {
                        var field = t.field;
                        $.ajax({
                      	  url:'http://localhost:8080/boot-crm/order_manage/order',
                            data:field,
                            dataType:'json',
                            type:'post',
                            success: function (data) {
                                  if(data.status==0){
                	                  //请求成功
                                	  layer.msg(data.msg);
                	                  //刷新表格数据
                                    i.reload("LAY-order-manage"), layer.close(e)
                                  }else{
                                	  layer.msg(data.msg,{
                                		  offset: '15px'
                                	      ,icon: 2
                                	      ,time: 3000
                                	  })
                                  }
                            },
                            error: function(data) {
                            	layer.msg('出错了！',{
                          		  offset: '15px'
                          	      ,icon: 2
                          	      ,time: 3000
                          	  })
                            }
                        });
                    }), r.trigger("click")
                },
                success: function (e, t) {
                	var body = layer.getChildFrame('body', t);
                	// 数据回显
                	body.find('#quantity').attr('lay-verify','required');
                	body.find('#payType').attr('lay-verify','required');
                	body.find('#customerName').attr('lay-verify','required');
                	
                	body.find('#LAY-customer-search').addClass('layui-hide');
                	body.find('#customerId').val(oldData.customerId);
                    body.find('#customerName').val(oldData.customerName);
                    body.find('#payType').val(oldData.payType);
                    
                    body.find('#customerId').attr('disabled','disabled');
                    body.find('#customerName').attr('disabled','disabled');
                    body.find('#quantity_label').html('续费周期');
                    body.find('#quantity').removeAttr('disabled');
                }
            })
        }
    }), i.render({
        elem: "#LAY-order-history",
        url: "http://localhost:8080/boot-crm/order_manage/orders/"+$('#customerId').val(),
        method: "get",
        parseData: function(res){ //res 即为原始返回的数据
            return {
              "code": res.status, //解析接口状态
              "msg": res.msg, //解析提示文本
              "count": res.data.total, //解析数据长度
              "data": res.data.list //解析数据列表
            };
        },
        cols: [
            [{
                field: "id",
                width: 80,
                align: "center",
                title: "订单ID"
            }, {
                field: "payType",
                title: "宽带类型",
                width: 100,
                align: "center",
                templet:"#table-order-type"
            }, {
                field: "quantity",
                width: 100,
                align: "center",
                title: "宽带周期",
                templet:"#table-order-quantity"
            }, {
                field: "payment",
                align: "center",
                width: 110,
                title: "付款金额(元)"
            }, {
                field: "payTime",
                align: "center",
                title: "付款时间"
            }, {
                field: "expiryTime",
                align: "center",
                title: "到期时间"
            }, {
                field: "operator",
                width: 100,
                align: "center",
                title: "经办人"
            }]
        ],
        page: !0,
        limit: 5,
        limits:[5,10,15,20],
        height: "300",
        text: { //自定义文本，此处用法--》当返回数据为空时的异常提示
        	none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        }
    }),e("useradmin", {})
});