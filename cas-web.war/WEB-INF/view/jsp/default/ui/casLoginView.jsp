<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:directive.include file="includes/top.jsp" />
<!-- 
<c:if test="${not pageContext.request.secure}">
    <div id="msg" class="errors">
        <h2>Non-secure Connection</h2>
        <p>You are currently accessing CAS over a non-secure connection.  Single Sign On WILL NOT WORK.  In order to have single sign on work, you MUST log in over HTTPS.</p>
    </div>
</c:if>
-->
<div class="box fl-panel" id="login">
    
    <form:form method="post" id="fm1" cssClass="fm-v clearfix" commandName="${commandName}" htmlEscape="true">
        <form:errors path="*" id="msg" cssClass="errors" element="div" />
        <!-- <spring:message code="screen.welcome.welcome" /> -->
		
		
		
		
		
		
		
		
		 <link rel="stylesheet" href="css/css/reset.min.css">

  <link rel='stylesheet prefetch' href='css/css/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='css/css/font-awesome.min.css'>

      <link rel="stylesheet" href="css/css/style.css">
	  <div class="container">
  <div class="card"></div>
		 <div class="card">
    <h1 class="title"><spring:message code="screen.welcome.instructions" /></h1>
    <form>
      <div class="input-container">
      <c:if test="${not empty sessionScope.openIdLocalId}">
                        <strong>${sessionScope.openIdLocalId}</strong>
                        <input type="hidden" id="username" name="username" value="${sessionScope.openIdLocalId}" />
                    </c:if>

                    <c:if test="${empty sessionScope.openIdLocalId}">
                        <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
                        <form:input cssClass="required"  cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="false" htmlEscape="true" />
                    </c:if>	
		
		
        <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        
		 <%--
                    NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
                    "autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
                    information, see the following web page:
                    http://www.geocities.com/technofundo/tech/web/ie_autocomplete.html
                    --%>
                    <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
                    <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />

        <label for="#{label}"><spring:message code="screen.welcome.label.password" /></label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
	   
                    <input type="hidden" name="lt" value="${loginTicket}" />
                    <input type="hidden" name="execution" value="${flowExecutionKey}" />
                    <input type="hidden" name="_eventId" value="submit" />
					<button name="submit" accesskey="l" tabindex="4"><span><spring:message code="screen.welcome.button.login" /></span></button>
              
      </div>
      <div class="footer"><a href="#"><spring:message code="screen.welcome.button.forgot" /></a></div>
    </form>
  </div>
  </div>
  </div>
   <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script  src="js/js/index.js"></script>

		
		
		
		<!-------------------------->
		
        <br></br></br>
    </form:form>
</div>
    <div id="sidebar">
    <p class="fl-panel fl-note fl-bevel-white fl-font-size-80"><spring:message code="screen.welcome.security" /></p>
    <div id="list-languages" class="fl-panel" style="display: none;">
        <%final String queryString = request.getQueryString() == null ? "" : request.getQueryString().replaceAll("&locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]|^locale=([A-Za-z][A-Za-z]_)?[A-Za-z][A-Za-z]", "");%>
        <c:set var='query' value='<%=queryString%>' />
        <c:set var="xquery" value="${fn:escapeXml(query)}" />
        <h3>Idiomas:</h3>
        <c:choose>
            <c:when test="${not empty requestScope['isMobile'] and not empty mobileCss}">
                <form method="get" action="login?${xquery}">
                    <select name="locale">
                        <option value="en">Ingl&eacute;s</option>
                        <option value="es">Espa&ntilde;ol</option>
                        <!--option value="fr">French</option>
                        <option value="ru">Russian</option>
                        <option value="nl">Nederlands</option>
                        <option value="sv">Svenskt</option>
                        <option value="it">Italiano</option>
                        <option value="ur">Urdu</option>
                        <option value="zh_CN">Chinese (Simplified)</option>
                        <option value="zh_TW">Chinese (Traditional)</option>
                        <option value="de">Deutsch</option>
                        <option value="ja">Japanese</option>
                        <option value="hr">Croatian</option>
                        <option value="cs">Czech</option>
                        <option value="sl">Slovenian</option>
                        <option value="pl">Polish</option>
                        <option value="ca">Catalan</option>
                        <option value="mk">Macedonian</option>
                        <option value="fa">Farsi</option>
                        <option value="ar">Arabic</option-->
                    </select>
                    <input type="submit" value="Switch">
                </form>
            </c:when>
            <c:otherwise>
                <c:set var="loginUrl" value="login?${xquery}${not empty xquery ? '&' : ''}locale=" />
                <ul
                    ><li class="first"><a href="${loginUrl}en">Ingl&eacute;s</a></li
                    ><li><a href="${loginUrl}es">Espa&ntilde;ol</a></li
                    ><!--li><a href="${loginUrl}fr">French</a></li
                    ><li><a href="${loginUrl}ru">Russian</a></li
                    ><li><a href="${loginUrl}nl">Nederlands</a></li
                    ><li><a href="${loginUrl}sv">Svenskt</a></li
                    ><li><a href="${loginUrl}it">Italiano</a></li
                    ><li><a href="${loginUrl}ur">Urdu</a></li
                    ><li><a href="${loginUrl}zh_CN">Chinese (Simplified)</a></li
                    ><li><a href="${loginUrl}zh_TW">Chinese (Traditional)</a></li
                    ><li><a href="${loginUrl}de">Deutsch</a></li
                    ><li><a href="${loginUrl}ja">Japanese</a></li
                    ><li><a href="${loginUrl}hr">Croatian</a></li
                    ><li><a href="${loginUrl}cs">Czech</a></li
                    ><li><a href="${loginUrl}sl">Slovenian</a></li
                    ><li><a href="${loginUrl}ca">Catalan</a></li
                    ><li><a href="${loginUrl}mk">Macedonian</a></li
                    ><li><a href="${loginUrl}fa">Farsi</a></li
                    ><li><a href="${loginUrl}ar">Arabic</a></li
                    ><li class="last"><a href="${loginUrl}pl">Polish</a></li
                    ></ul-->
                </c:otherwise>
            </c:choose>
    </div>
</div>
<script language="javascript">
//     $('#username').tooltip({
//     	showBody: " - "
//     });
</script>
<jsp:directive.include file="includes/bottom.jsp" />