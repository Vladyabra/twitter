<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Tweets you never asked for</title>
        <sec:csrfMetaTags />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="/css/twitter.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="/js/twitter.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="span12">
                <div class="row top20">
                    <div class="col-md-1 col-xs-1" style="text-align: center;">
                        <a href="https://ru.gravatar.com/"><img id="heregravatar" width=30 height=30></a>
                    </div>
                    <div class="col-md-2 col-xs-10">
                        Hello, <span id="herename" class="label label-info">%username%</span>!
                    </div>
                    <div class="col-md-2 col-xs-1">
                        <sf:form action="/logout"><input type="submit" class="btn btn-danger" value="Sign out"></sf:form>
                    </div>
                    <div class="col-md-6 col-xs-11">
                            <input id="text" type="text" class="form-control input" maxlength=140 />
                    </div>
                    <div class="col-md-1 col-xs-1">
                            <input type="button" class="btn btn-primary" value="Tweet!" onclick="addMessage();" />
                    </div>
                </div>
                <div class="feed"></div>
                <div id="loadMore" class="row top10" style="display: none;"><div class="col-bg-12"><center><input type="button" class="btn btn-sm btn-link" value="More tweets" onclick="loadMore();" /></div></center></div>
            </div>
        </div>
    </body>
</html>