<div class="container">
    <header class="masthead">
        <div class="row">
            <div class="col-lg-6 col-sm-6 col-xs-12">
                <h3>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                        <img src="${pageContext.request.contextPath}/img/favicon.ico" width="16" height="16" alt="Home page">Library Online&trade;
                    </a>
                </h3>
            </div>
            <div class="col-lg-3 col-sm-6 col-xs-12 header-comps">
                <sec:authorize access="isAuthenticated()">
                    <div class="float-right">
                        <div class="float-left">
                            <p><sec:authentication property="principal.username" />&nbsp;&nbsp;</p>
                        </div>
                        <div class="float-right">
                            <a class="btn-secondary btn-sm" role="button" id="logoutBut"
                               href="/logout"><spring:message code="logout" /></a>
                        </div>
                    </div>
                </sec:authorize>
            </div>
            <div class="col-lg-3 col-sm-12 col-xs-12 header-comps">
                <a class="language" href="${pageContext.request.contextPath}?lang=en">en</a>
                <a class="language" href="${pageContext.request.contextPath}?lang=ru">ru</a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-sm-12 col-xs-12">
                <sec:authorize access="isAuthenticated()">
                    <nav class="navbar navbar-expand-md navbar-light bg-light rounded mb-3">
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
                    aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarCollapse">
                            <ul class="navbar-nav text-md-center nav-justified w-100">
                                <sec:authorize access="hasRole('ROLE_READER')">
                                    <%@ include file="readerMenu.jsp" %>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <%@ include file="adminMenu.jsp" %>
                                </sec:authorize>
                            </ul>
                        </div>
                    </nav>
                </sec:authorize>
            </div>
        </div>
    </header>
</div>
