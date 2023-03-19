CREATE TABLE IF NOT EXISTS task_category
(
    id SERIAL PRIMARY KEY,
    task_id INT REFERENCES tasks(id),
    category_id INT REFERENCES categories(id)
)