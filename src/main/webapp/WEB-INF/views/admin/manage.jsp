<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../template/admin_header.jsp"/>
<a href="/adminpanel/logout">Wyloguj się</a>
<p>Jeśli słowo jest prawidłowe zaakceptuj je. Jeśli nie, kliknij "Odrzuć" i wpisz przyczynę.<br>
    Nie będziesz sprawdzał tych samych słów, gdyż raz zatwierdzone lub odrzucone słowa są zapamiętywane (nie występują
    duplikaty)<br>
    Zwracaj uwagę na wielkość liter, ewentualnie dokonaj poprawy.<br>
    Możesz poprawić słowo (np. jak ktoś zrobił literówkę), ale zachowaj rozwagę.<br>
    <strong>Jeśli wystąpi błąd: <i>500 - INTERNAL SERVER ERROR</i> lub inny problem powiadom administratora (Horego)</strong>
<p>Słów do sprawdzenia: <strong>${count}</strong></p>
<span style="color: red;"><c:out value="${error}"></c:out></span>
<c:forEach var="word" items="${words}">
    <form action="/adminpanel/manage" method="post">
        <input type="text" value="${word.word}" data-org-word="${word.word}" name="word">
        <input type="submit" value="Akceptuj">
        <button class="reject">Odrzuć</button>
        <input type="text" name="reject_reason" placeholder="podaj powód" style="display: none;">
        <input type="submit" value="OK" style="display: none;">
        <input type="hidden" name="word_id" value="${word.id}">
        <input type="hidden" name="accept" value="1">
    </form>
</c:forEach>
<jsp:include page="../template/doc_footer.jsp"/>