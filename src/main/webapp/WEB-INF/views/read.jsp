<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게시글 읽기</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">킴보드</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/freeboard">자유게시판</a></li>
            <li><a href="#">익명게시판</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:if test ="${sessionScope.logininfo == null}">
                <li><a href="/join"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </c:if>
            <c:if test ="${sessionScope.logininfo != null}">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>${sessionScope.logininfo.nickname}</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </c:if>
        </ul>
        <form class="navbar-form navbar-left" action="/action_page.php">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="전체검색" name="search">
            </div>
            <button type="submit" class="btn btn-default">찾기</button>
        </form>
    </div>
</nav>

<div class="container table-bordered" style="padding-top: 15px;">
    <h1 style="text-align:center"> 게시글</h1>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">제목</h3>
        </div>
        <div class="panel-body">
            ${board.title}
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">작성자</h3>
        </div>
        <div class="panel-body" >
            ${board.nickname}
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">작성 시간</h3>
        </div>
        <div class="panel-body"  >
            ${board.regdate}
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">내용</h3>
        </div>
        <div class="panel-body">
            ${board.content}
        </div>
    </div>
    <button type="cancel" class="pull-right btn btn-default"><a href="/freeboard">목록</a></button>
    <button type="submit" class="pull-right btn btn-default"><a href="/rewrite?post_id=${board.post_id}">답글</a></button>
    <button type="submit" class="pull-right btn btn-default"><a href="/modify?post_id=${board.post_id}">수정</a></button>
    <button type="submit" class="pull-right btn btn-default" data-toggle="modal" data-target="#myModal">삭제</button>

    <!-- Modal  -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Real True Delete?</h4>
                </div>
                <div class="modal-body">
                    </form>
                    <div class="form-group">
                        <label>정말로 삭제 하시겠습니까?</label>

                    </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"><a href="/delete?post_id=${board.post_id}">확인</a></button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>