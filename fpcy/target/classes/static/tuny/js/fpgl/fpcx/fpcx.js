var prefix = "/fpgl/fpcx"

$(function () {
    var columns = [
        {
            checkbox: true
        }, {
            field: 'id',
            title: '主键',
            visible: false
        }, {
            field: 'fpdm',
            title: '发票代码'
        }, {
            field: 'fphm',
            title: '发票号码'
        }, {
            field: 'gfmc',
            title: '购方名称',
            formatter: function (value, row, index) {
                if (value == '' || value == undefined) {
                    return row['gfdw'];
                } else {
                    return value;
                }
            }
        }, {
            field: 'xfmc',
            title: '销方名称',
            formatter: function (value, row, index) {
                if (value == '' || value == undefined) {
                    return row['mfdw'];
                } else {
                    return value;
                }
            }
        }, {
            field: 'kprq',
            title: '开票日期',
            formatter: function (value, row, index) {
                if(value)
                return value.substring(0,4) + '-' + value.substring(4,6) + '-' + value.substring(6,8);
            }
        }, {
            field: 'bz',
            title: '备注'
        }, {
            field: 'jshj',
            title: '合计',
            formatter: function (value, row, index) {
                if (row.fplx == 01) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 10) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 04) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 14) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 03) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 11) {
                    return '<span class="label label-success">' +fmoney(value)+ '</span>';
                } else if (row.fplx == 15) {
                    return '<span class="label label-success">' +fmoney(row.cjhj)+ '</span>';
                } else {
                    return '<span class="label label-success">其他</span>';
                }
            }
        },{
            field: 'zfbz',
            title: '作废标志',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 1) {
                    return '<span class="label label-danger">作废</span>';
                } else {
                    return '<span class="label label-success">正常</span>';
                }
            }
        },
        {
            field: 'fplx',
            title: '发票类型',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 01) {
                    return '<span class="label label-success">增值税专用发票</span>';
                } else if (value == 10) {
                    return '<span class="label label-success">增值税电子发票</span>';
                } else if (value == 04) {
                    return '<span class="label label-success">增值税普通发票</span>';
                } else if (value == 14) {
                    return '<span class="label label-success">通行费发票</span>';
                } else if (value == 03) {
                    return '<span class="label label-success">机动车销售统一发票</span>';
                } else if (value == 11) {
                    return '<span class="label label-success">卷式发票</span>';
                } else if (value == 15) {
                    return '<span class="label label-success">二手车销售统一发票</span>';
                } else {
                    return '<span class="label label-success">其他</span>';
                }
            }
        },
        {
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-warning btn-sm" href="#" title="详细信息" onclick="detail(\'' + row.id + '\')"><i class="fa fa-search"></i></a>');
                return actions.join('');
            }
        }];
    var url = prefix + "/list";
    $.initTable(columns, url);
});

/*操作日志-详细*/
function detail(id) {
    var url = prefix + '/detail/' + id;
    layer_show("发票详情", url, '1000', '600');
}

// 批量删除
function batchRemove() {
    var rows = $.getSelections("operId");
    if (rows.length == 0) {
        $.modalMsg("请选择要删除的数据", "warning");
        return;
    }
    $.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function (r) {
        _ajax(prefix + '/batchRemove', {"ids": rows}, "post", r);
    });
}
