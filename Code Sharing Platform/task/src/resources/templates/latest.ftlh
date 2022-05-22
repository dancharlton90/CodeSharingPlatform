<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<table>
    <#list code_snippets as code_snippet>
        <tr>
            <span id="load_date">${code_snippet.date}</span>
            <pre id="code_snippet"><code>${code_snippet.code}</code></pre>
        </tr>
    </#list>
</table>
</body>
</html>