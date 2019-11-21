<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/17
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty user}">
    <div align="center">
        当前用户：${user.name}
    </div>
</c:if>

<table align='center' border='1' cellspacing='0'>
    <tr>
        <td>id</td>
        <td>名称</td>
        <td>价格</td>
        <td>购买</td>
    </tr>
    <c:forEach  items="${list}" var="p" varStatus="st" >
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>
                <form action="addOrderItem" method="post">
                    <input type="text" value="0" name="num">
                    <input type="hidden" name = "pid" value="${p.id}">
                    <input type="submit" value="购买">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>