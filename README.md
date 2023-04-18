This is a todolist API created in Spring boot. To use the API do the following:
  1. Run the spring boot application
  2. Open port on localhost (:8080) Note: you'll see an error unless you add /todos.
  3. Use curl or Postman to make POST request with a new todolist item. Include a name and description in JSON format
  4. An id will automatically be created by the repository when the item is posted
  5. You can lookup all todolist items with /todos or specific items with /todos/{id}
  6. You can edit the todolist item with a PUT command
