function goBack() {
    window.history.back();
}

function confirmDelete(todoName) {
    return confirm(`Are you sure you want to delete '${todoName}'?`);
}