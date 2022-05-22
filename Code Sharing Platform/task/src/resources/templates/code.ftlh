<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
<!--    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>-->
</head>
<body>
    <span id="load_date">${code_snippet.date}</span>
    <p></p>
    <#if code_snippet.hasViewsLimit()>
        <span id="views_restriction"><u>${code_snippet.views}</u> more views allowed</span>
    </#if>
    <p></p>
    <#if code_snippet.expiryTime?has_content>
        <span id="time_restriction">The code will be available for ${duration_remaining} seconds</span>
    </#if>
    <pre id="code_snippet"><code>${code_snippet.code}</code></pre>
</body>
</html>