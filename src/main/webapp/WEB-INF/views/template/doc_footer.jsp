<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%
    int year = LocalDate.now().getYear();
%>
<div class="footer">
    <div>Copyright © <%=year%> by Hory314, SMOK, feitur</div>
</div>
</body>
</html>
