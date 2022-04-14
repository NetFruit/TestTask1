
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="post" action="filter" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?if_exists}" placeholder="Search by news site">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

List of articles
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Text</th>
            <th>News Site</th>
            <th>Published at</th>
        </tr>
        </thead>
        <tbody>
        <#list articles as article>
            <tr>
                <td>${article.id}</td>
                <td>${article.title}</td>
                <td>${article.summary}</td>
                <td>${article.newsSite}</td>
                <td>${article.publishedAt}</td>

            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/main">Main page</a>
