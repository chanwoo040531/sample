const apiUrl = 'http://localhost:8090/v1/articles';

document.getElementById('createForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, content })
    });

    if (response.ok) {
        alert('성공적으로 게시글이 생성되었습니다.');
        loadArticles();
        document.getElementById('title').value = '';
        document.getElementById('content').value = '';
    } else {
        alert('게시글 생성에 실패했습니다.');
    }
});

async function loadArticles() {
    const response = await fetch(apiUrl);
    const data = await response.json();

    const articleList = document.getElementById('articleList');
    articleList.innerHTML = '';

    data.body.forEach(article => {
        const li = document.createElement('li');
        li.textContent = article.title;
        li.dataset.id = article.id;
        li.addEventListener('click', () => showArticleDetail(article.id));
        articleList.appendChild(li);
    });
}

async function showArticleDetail(id) {
    const response = await fetch(`${apiUrl}/${id}`);
    const data = await response.json();
    const article = data.body;

    document.getElementById('articleDetail').classList.remove('hidden');
    document.getElementById('editForm').classList.add('hidden');
    document.getElementById('detailTitle').textContent = article.title;
    document.getElementById('detailContent').textContent = article.content;
    document.getElementById('detailCreatedAt').textContent = `생성일: ${article.createdAt}`;
    document.getElementById('detailLastUpdatedAt').textContent = `수정일: ${article.lastUpdatedAt}`;
    document.getElementById('editButton').onclick = () => showEditForm(article);
    document.getElementById('deleteButton').onclick = () => deleteArticle(article.id);
}

function showEditForm(article) {
    document.getElementById('articleDetail').classList.add('hidden');
    document.getElementById('editForm').classList.remove('hidden');
    document.getElementById('editTitle').value = article.title;
    document.getElementById('editContent').value = article.content;
    document.getElementById('updateButton').onclick = () => updateArticle(article.id);
    document.getElementById('cancelButton').onclick = () => {
        document.getElementById('editForm').classList.add('hidden');
        document.getElementById('articleDetail').classList.remove('hidden');
    };
}

async function updateArticle(id) {
    const title = document.getElementById('editTitle').value;
    const content = document.getElementById('editContent').value;

    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, content })
    });

    if (response.ok) {
        alert('성공적으로 게시글이 수정되었습니다.');
        loadArticles();
        document.getElementById('editForm').classList.add('hidden');
        document.getElementById('articleDetail').classList.add('hidden');
    } else {
        alert('게시글 수정에 실패했습니다.');
    }
}

async function deleteArticle(id) {
    const response = await fetch(`${apiUrl}/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        alert('성공적으로 게시글이 삭제되었습니다.');
        loadArticles();
        document.getElementById('articleDetail').classList.add('hidden');
    } else {
        alert('게시글 삭제에 실패했습니다.');
    }
}

loadArticles();
