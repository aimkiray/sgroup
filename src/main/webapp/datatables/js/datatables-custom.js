/**
 * Created by nekuata on 2017/7/19.
 */
// $(document).ready( function () {
//     $('#table_id_example').DataTable({
//         language: {
//             url: '/datatables/json/chinese.json'
//         },
//         searching: true,
//         ordering: true,
//         serverSide: true,
//         ajax: {
//             url: '/productservlet.do?operate=queryProductToDataTables',
//             dataSrc: 'data',
//             type: 'post'
//         },
//         columns: [
//             {data: 'productName'},
//             {data: 'productPrice'},
//             {data: 'number'},
//             {data: 'productType.typeName'},
//             {data: 'productTime'},
//             {data: 'fileName'}
//         ]
//     });
// });

var editor; // use a global for the submit and return data rendering in the examples

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
        ajax: "/productservlet.do?operate=queryProductToDataTables",
        table: "#example",
        fields: [ {
            label: "名称:",
            name: "productName"
        }, {
            label: "价格:",
            name: "productPrice"
        }, {
            label: "数量:",
            name: "number"
        }, {
            label: "类别:",
            name: "productType.typeName"
        }, {
            label: "入库时间:",
            name: "productTime",
            type: "datetime"
        }, {
            label: "文件名:",
            name: "fileName"
        }
        ]
    } );

    // Activate an inline edit on click of a table cell
    $('#example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this );
    } );

    $('#example').DataTable( {
        language: {
            url: '/datatables/json/chinese.json'
        },
        dom: "Bfrtip",
        ajax: "/productservlet.do?operate=queryProductToDataTables",
        order: [[ 1, 'asc' ]],
        columns: [
            {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
            {data: 'productName'},
            {data: 'productPrice'},
            {data: 'number'},
            {data: 'productType.typeName'},
            {data: 'productTime'},
            {data: 'fileName'}
        ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
} );