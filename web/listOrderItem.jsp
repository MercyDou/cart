<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/20
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1 align="center">购物车</h1>
<table align="center" border="1" cellspacing="0">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>小计</td>
        <td>删除</td>
    </tr>
    <c:forEach items="${ois}" var="o" varStatus="st">
        <tr>
        <td>${o.product.name}</td>
        <td>${o.product.price}</td>
        <td>${o.num}</td>
        <td>${o.product.price*o.num}</td>
        <td><a href="/deleteOrderItem?id=${o.product.id}">删除</a> </td>
        </tr>
    </c:forEach>
    <c:if test="${!empty ois}">
        <tr>
            <td colspan="4" align="right">
                <a href="creatOrder">生成订单</a>
            </td>
        </tr>
    </c:if>
</table>

