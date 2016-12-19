<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Your personal Twitter is one step from you</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="/js/videobg.js"></script>
        <link href="/css/signin.css" rel="stylesheet">
        <link href="/css/videobg.css" rel="stylesheet">
    </head>
    <body>
        <div class="homepage-hero-module">
            <div class="video-container">
                <div class="filter">

                <div class="container">
                  <form class="form-signin" method="POST">
                    <h2 class="form-signin-heading">Please sign in</h2>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> No matching found.
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success fade in">
                            <strong>Bye!</strong> Can`t wait to see you back
                        </div>
                    </c:if>

                    <label for="username" class="sr-only">Username</label>
                    <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <center>Don`t have an account?
                    <br><a href="/register">Register now for free!</a></center>
                  </form>

                </div> <!-- /container -->

                </div>
                <video autoplay loop class="fillWidth">
                    <source src="/video/bg.mp4" type="video/mp4" />Your browser does not support the video tag. I suggest you upgrade your browser.
                    <source src="/vide/bg.webm" type="video/webm" />Your browser does not support the video tag. I suggest you upgrade your browser.
                </video>
                <div class="poster hidden">
                    <img src="/img/bg.jpg" alt="">
                </div>
            </div>
        </div>


    </body>

</html>