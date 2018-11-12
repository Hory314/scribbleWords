<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/doc_header.jsp"/>
<div class="box">
    <form action="/" method="post">
        <select disabled>
            <option>Polish</option>
        </select>
        <label>
            <div>Wpisz poniżej słowa, które chcesz dodać do bazy.<br>
                Możesz wpisać wiele słów na raz oddzielając je przecinkiem.<br>
                Każde słowo musi mieć minimum 3 znaki, a maksymalnie 32 (włącznie ze znakami specjalnymi)<br>
                Nie używaj znaków specjalnych oprócz przecinka, myślnika i spacji.<br>
                Znaki w języku polskim są dozwolone. Używaj wielkich liter kiedy potrzeba.<br>
                Słowa zostaną dodane do bazy dopiero po sprawdzeniu przez Admnistratora.
            </div>
            <textarea name="new_words" placeholder="słowa,oddzielone,przecinkiem" required autofocus></textarea>
        </label>
        <input type="submit" value="Dodaj słowa">
    </form>
</div>
<%-- POST status box --%>
<c:if test="${postResult}">
    <div class="box" style="/*display: none;*/">
        <p style="color: green;">Dodano: <strong>${successCount}</strong></p>
        <p style="color: red;">Odrzucono: <strong>${failCount}</strong>
            <c:if test="${failCount != '0'}"> z następujących powodów: ${error}</p>
        <table>
            <tbody>
            <c:forEach items="${incorrectWords}" var="entry">
                <tr>
                    <td style="color: red;">${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
    </div>
</c:if>
<%-- POST status box --%>

<div class="box">
    <p>Liczba słów w bazie: <strong>${words_count}</strong></p>
    <p>Skopij słowa poniższym przyciskiem i wklej je do formularza podczas tworzenia prywatnej gry.</p>
    <form id="words">
        <textarea readonly><c:forEach varStatus="loop" var="word" items="${words}">${word.word}<c:if
                test="${!loop.last}">,</c:if></c:forEach></textarea>
    </form>
    <button id="copy_words">
        <div class="copied" style="display: none;">
            <div class="arrow"></div>
            <span>Skopiowano <strong>${words_count}</strong>!</span>
        </div>
        Kopiuj do schowka
        <svg style="fill: white;" viewBox="0 0 14 16" version="1.1" width="25" height="25" aria-hidden="true">
            <path fill-rule="evenodd"
                  d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
        </svg>
    </button>
    <span style="display: block; color: red;text-align: center;">${error}</span>
    <a class="game-link" style="display: none;" href="https://skribbl.io/">skribbl.io</a>
</div>
<jsp:include page="template/doc_footer.jsp"/>