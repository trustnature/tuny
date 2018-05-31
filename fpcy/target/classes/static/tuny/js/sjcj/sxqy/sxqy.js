var prefix = "/sjcj/sxqy"


$(document).ready(function(){
    queryCompyList();
});

function queryCompyList() {
    var columns = [{
        checkbox: true
    },
        {
            field: 'userId',
            title: '用户ID'
        },
        {
            field: 'loginName',
            title: '登录名称'
        },
        {
            field: 'userName',
            title: '用户名称'
        },
        {
            field: 'email',
            title: '邮箱'
        },
        {
            field: 'phonenumber',
            title: '手机'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '0') {
                    return '<span class="label label-success">正常</span>';
                } else if (value == '1') {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        },
        {
            field: 'createTime',
            title: '创建时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                if(row.userType == "N") {
                    var actions = [];
                    actions.push('<a class="btn btn-primary btn-sm ' + editFlag + '" href="#" title="编辑" onclick="edit(\'' + row.userId + '\')"><i class="fa fa-edit"></i></a> ');
                    actions.push('<a class="btn btn-warning btn-sm ' + removeFlag + '" href="#" title="删除" onclick="remove(\'' + row.userId + '\')"><i class="fa fa-remove"></i></a> ');
                    actions.push('<a class="btn btn-success btn-sm ' + resetPwdFlag + '"  href="#" title="重置" onclick="resetPwd(\'' + row.userId + '\')"><i class="fa fa-key"></i></a>');
                    return actions.join('');
                } else {
                    return "";
                }
            }
        }];
    var url = prefix + "/list";
    $.initTable(columns, url);
}

/*用户管理-删除*/
function remove(compyId) {
    $.modalConfirm("确定要删除选中企业吗？", function() {
        _ajax(prefix + "/remove/" + compyId, "", "post");
    })
}

/*用户管理-修改*/
function edit(userId) {
    var url = prefix + '/edit/' + userId;
    layer_showAuto("修改企业", url);
}

/*用户管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_showAuto("新增企业", url);
}

