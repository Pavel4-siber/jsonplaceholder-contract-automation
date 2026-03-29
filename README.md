# JSONPlaceholder API Contract Automation

Проект автоматизированного тестирования API [jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com) с использованием контрактного подхода (Schema-first).

## 🚀 Технологический стек 2026
*   **Java 21+** (LTS) — использование современных возможностей языка.
*   **Gradle 8.x/9.x** — декларативная сборка на Kotlin DSL и Version Catalogs.
*   **RestAssured 5.x** — DSL для работы с HTTP-запросами.
*   **JSON Schema to POJO** — автоматическая генерация моделей данных из схем.
*   **Allure Report** — интерактивная отчетность с историей запусков.
*   **GitHub Actions** — настроенный CI/CD пайплайн.

## 🏗 Архитектура проекта
Проект следует принципу **Contract-Driven Testing**:
1.  **Схемы (`src/main/resources/schemas/`)**: JSON-схемы являются единственным источником правды для моделей данных.
2.  **Генерация**: Gradle плагин `jsonschema2pojo` автоматически создает Java-классы (POJO) с поддержкой Builder-паттерна.
3.  **Clients**: Инкапсуляция логики запросов в типизированные клиенты (`PostClient`), наследуемые от `BaseClient` с встроенными фильтрами Allure.

## 🛠 Быстрый старт

### 1. Генерация моделей из схем
Перед запуском тестов необходимо сгенерировать Java-классы из JSON-файлов:
```bash
./gradlew generateJsonSchema2Pojo
