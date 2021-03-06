<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'borrow.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link href="css/index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#next").click(function(){
                var pages = parseInt($("#pages").html());
                var page = parseInt($("#currentPage").html());
                if(page == pages){
                    return;
                }
                page++;
                location.href = "/book?method=findAllBorrow&page="+page;
            })

            $("#previous").click(function () {
                var page = parseInt($("#currentPage").html());
                if(page == 1){
                    return;
                }
                page--;
                location.href = "/book?method=findAllBorrow&page="+page;
            })

            $("#first").click(function () {
                location.href = "/book?method=findAllBorrow&page=1";
            })

            $("#last").click(function(){
                var pages = parseInt($("#pages").html());
                location.href = "/book?method=findAllBorrow&page="+pages;
            })
        })
    </script>
</head>

<body>
<%@ include file="top.jsp" %>


<div id="main">
    <div class="navigation">
        ????????????:&nbsp;&nbsp;<a href="/book?page=1">??????</a>
        <div id="readerBlock">????????????&nbsp;:${reader.username }&nbsp;<a href="/logout">??????</a></div>
    </div>
    <div class="img_block">
        <img src="images/main_booksort.gif" class="img" />
    </div>

    <table class="table" cellspacing="0">
        <tr>
            <td>??????</td>
            <td>????????????</td>
            <td>????????????</td>
            <td>?????????</td>
            <td>????????????</td>
            <td>????????????</td>
            <td>????????????</td>
            <td>????????????</td>
            <td>????????????</td>
            <td>????????????</td>
        </tr>
        <c:forEach items="${list}" var="borrow">
            <tr>
                <td>${borrow.id}</td>
                <td>${borrow.book.name}</td>
                <td>${borrow.book.author}</td>
                <td>${borrow.book.publish}</td>
                <td>${borrow.reader.name}</td>
                <td>${borrow.reader.tel}</td>
                <td>${borrow.reader.cardId}</td>
                <td>${borrow.borrowTime}</td>
                <td>${borrow.returnTime}</td>
                <td>
                    <c:if test="${borrow.state == 0}">
                        <font color="black">?????????</font>
                    </c:if>
                    <c:if test="${borrow.state == 1}">
                        <font color="green">????????????</font>
                    </c:if>
                    <c:if test="${borrow.state == 2}">
                        <font color="red">?????????</font>
                    </c:if>
                    <c:if test="${borrow.state == 3}">
                        <font color="yellow">?????????</font>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

    </table>
    <hr class="hr"/>
    <div id="pageControl">
        <div class="pageControl_item">??????<font id="dataPrePage">${dataPrePage }</font>?????????</div>
        <div class="pageControl_item" id="last">????????????</div>
        <div class="pageControl_item" id="next">?????????</div>
        <div class="pageControl_item"><font id="currentPage">${currentPage }</font>/<font id="pages">${pages }</font></div>
        <div class="pageControl_item" id="previous">?????????</div>
        <div class="pageControl_item" id="first">??????</div>
    </div>
</div>


<%@ include file="footer.jsp" %>
</body>
</html>
