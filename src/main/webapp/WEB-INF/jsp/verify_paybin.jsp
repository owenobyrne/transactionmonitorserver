<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
  <title>Payb.in</title>
  <link type="text/css" rel="stylesheet" href="<c:url value="/style.css"/>"/>
</head>

<body>

  <h1>Payb.in</h1>

  <div id="content">

      The access token is <c:out value="${access_token.token}"/> and the secret is <c:out value="${access_token.tokenSecret}"/>
  </div>


</body>
</html>
