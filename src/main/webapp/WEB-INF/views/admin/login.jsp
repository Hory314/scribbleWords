<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/doc_header.jsp"/>
<h1>Zaloguj się jako administrator</h1>
<strong><a style="color: black;" href="<c:url value="/"/>">≪ Powrót do strony głównej</a></strong>
<hr>
<span style='color:
<c:if test="${login_info_success eq '0'}">red</c:if>
<c:if test="${login_info_success eq '1'}">green</c:if>;'><c:out value="${login_info}"/></span>
<form action="<c:url value="/adminpanel" />" method="post">
    <p><input type="text" name="login" placeholder="Login"/></p>
    <p><input type="password" name="password" placeholder="Hasło"/></p>
    <p><label><input type="checkbox" name="remember"> <b>Zapamiętaj mnie</b></label></p>
    <p><input type="submit" value="Zaloguj"></p>
</form>
<jsp:include page="../template/doc_footer.jsp"/>