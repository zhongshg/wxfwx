/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#counts").keyup(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#countsspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#countsspan").html("请输入正确的数字");
        } else {
            $("#countsspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#countsspan").html("");
        }
    });
    $("#counts").blur(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#countsspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#countsspan").html("请输入正确的数字");
        } else {
            $("#countsspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#countsspan").html("");
        }
    });
    $("#taketimes").keyup(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#taketimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#taketimesspan").html("请输入正确的数字");
        } else {
            $("#taketimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#taketimesspan").html("");
        }
    });
    $("#taketimes").blur(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#taketimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#taketimesspan").html("请输入正确的数字");
        } else {
            $("#taketimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#taketimesspan").html("");
        }
    });
    $("#pointtimes").keyup(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#pointtimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#pointtimesspan").html("请输入正确的数字");
        } else {
            $("#pointtimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#pointtimesspan").html("");
        }
    });
    $("#pointtimes").blur(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#pointtimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#pointtimesspan").html("请输入正确的数字");
        } else {
            $("#pointtimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#pointtimesspan").html("");
        }
    });
    $("#changetimes").keyup(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#changetimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#changetimesspan").html("请输入正确的数字");
        } else {
            $("#changetimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#changetimesspan").html("");
        }
    });
    $("#changetimes").blur(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#changetimesspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#changetimesspan").html("请输入正确的数字");
        } else {
            $("#changetimesspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#changetimesspan").html("");
        }
    });
    $("#num").keyup(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#numspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#numspan").html("请输入正确的数字");
        } else {
            $("#numspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#numspan").html("");
        }
    });
    $("#num").blur(function() {
        if (!/^\d+$/.test($(this).val())) {
            $("#numspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#numspan").html("请输入正确的数字");
        } else {
            $("#numspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#numspan").html("");
        }
    });
    $("#changepercent").keyup(function() {
        if ("" == $("#changepercent").val() || !/^[0-9]+([.]{1}[0-9]+){0,1}$/.test($("#changepercent").val()) || 0 > $("#changepercent").val() || 1 < $("#changepercent").val()) {
            $("#changepercentspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#changepercentspan").html("请输入正确的数字");
        } else {
            $("#changepercentspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#changepercentspan").html("");
        }
    });
    $("#changepercent").blur(function() {
        if ("" == $("#changepercent").val() || !/^[0-9]+([.]{1}[0-9]+){0,1}$/.test($("#changepercent").val()) || 0 > $("#changepercent").val() || 1 < $("#changepercent").val()) {
            $("#changepercentspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#changepercentspan").html("请输入正确的数字");
        } else {
            $("#changepercentspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#changepercentspan").html("");
        }
    });
    $("#discounts").keyup(function() {
        if ("" == $("#discounts").val() || !/^[0-9]+([.]{1}[0-9]+){0,1}$/.test($("#discounts").val()) || 0 > $("#discounts").val() || 1 < $("#discounts").val()) {
            $("#discountsspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#discountsspan").html("请输入正确的数字");
        } else {
            $("#discountsspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#discountsspan").html("");
        }
    });
    $("#discounts").blur(function() {
        if ("" == $("#discounts").val() || !/^[0-9]+([.]{1}[0-9]+){0,1}$/.test($("#discounts").val()) || 0 > $("#discounts").val() || 1 < $("#discounts").val()) {
            $("#discountsspan").removeClass("input-notification success png_bg").addClass("input-notification error png_bg");
            $("#discountsspan").html("请输入正确的数字");
        } else {
            $("#discountsspan").removeClass("input-notification error png_bg").addClass("input-notification success png_bg");
            $("#discountsspan").html("");
        }
    });
});