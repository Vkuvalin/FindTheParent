<div align="center">

# 🧸 Find The Parent

**Android-игра на память, созданная как личный проект для дочери**

`Kotlin` · `Jetpack Compose` · `MVVM` · `Clean Architecture` · `Dagger 2` · `Room`

</div>

## О проекте

**Find The Parent** — небольшая Android-игра на память, в которой ребёнок открывает карточки и ищет нужные изображения родителей.

Проект создавался как самостоятельное практическое приложение: с игровым циклом, несколькими уровнями сложности, локальным хранением состояния и счёта, настройками интерфейса и полноценной Compose-навигацией.

Доступны четыре режима сложности:

- **Easy** — 12 карточек, сетка в 3 колонки.
- **Medium** — 16 карточек, сетка в 4 колонки.
- **Hard** — 20 карточек, сетка в 4 колонки.
- **Special** — отдельный режим на 20 карточек.

## Возможности

- Загрузка пользовательских фотографий родителей.
- Игровое поле с переворачивающимися карточками.
- Несколько режимов сложности.
- Подсчёт и сохранение счёта.
- Настройка внешнего вида карточек.
- Управление звуком и частью визуальных настроек приложения.
- Стартовые / welcome-экраны с анимациями.
- Локальное сохранение игровых данных.
- Навигация между основными экранами приложения.

## Архитектура

Проект разделён на основные слои:

```text
Presentation
   │
   ├── Jetpack Compose UI
   ├── ViewModel
   └── Navigation
          │
          ▼
Domain
   │
   ├── Entities
   ├── Use Cases
   └── Repository contracts
          │
          ▼
Data
   │
   ├── Repository implementation
   ├── Mappers
   └── Room database
```

Для dependency injection используется **Dagger 2**.

## Технологии

| Категория | Использовано |
| --- | --- |
| Язык | Kotlin |
| Платформа | Android |
| UI | Jetpack Compose, Material 3 |
| Архитектура | MVVM + Clean Architecture |
| Dependency Injection | Dagger 2 |
| Локальное хранение | Room |
| Асинхронность | Kotlin Coroutines |
| Навигация | Navigation Compose |
| Изображения | Coil / Glide / Picasso |
| Сборка | Gradle Kotlin DSL |

## Экраны

Основной пользовательский сценарий включает:

- **Welcome / Splash** — стартовые экраны и анимации.
- **Главный экран** — фотографии родителей, текущий счёт и запуск игры.
- **Настройки** — параметры оформления и поведения приложения.
- **Выбор сложности** — Easy / Medium / Hard / Special.
- **Игровой экран** — сетка карточек, открытие изображений и определение результата раунда.

## Фотогалерея

<p float="left">
  <img src="https://github.com/user-attachments/assets/b008d516-5eeb-4c27-9418-83323b184d38" width="32%" />
  <img src="https://github.com/user-attachments/assets/b0e18c91-ed58-44d9-9367-0f9ee70dd916" width="32%" />
  <img src="https://github.com/user-attachments/assets/9485f1bd-dd7f-4ae7-a134-1339d212bb64" width="32%" />
</p>

<p float="left">
  <img src="https://github.com/user-attachments/assets/16d0f6f5-7b62-435d-b663-51d10d60a29e" width="24%" />
  <img src="https://github.com/user-attachments/assets/4559d113-e17b-45e9-807c-1c5647b97acd" width="24%" />
  <img src="https://github.com/user-attachments/assets/12bcb5ad-65c3-4766-a1d7-87fb4ea00be3" width="24%" />
  <img src="https://github.com/user-attachments/assets/7bc787fb-c5d8-42e2-aa7e-4955a965fe77" width="24%" />
</p>

## Статус

Разработка проекта остановлена. Репозиторий сохранён как личный Android-проект и этап развития навыков проектирования приложений на Kotlin и Jetpack Compose.

Основная функциональность и исходная архитектура сохранены без попытки переписать проект под текущий стек.
