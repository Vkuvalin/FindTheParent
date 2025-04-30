# 🎮 Find The Parents (Kotlin/Android/Jetpack Compose)

**Find The Parents** — это тренировочное первое самостоятельное приложение = небольшая игра на память для ребенка. Имеет 4 режима сложности EASY, MEDIUM, HARD, SPECIAL.
Проект написан на **Kotlin + Jetpack Compose**, с современным UI/UX, модульной архитектурой и чистым кодом.

---

## 🧱 Архитектура проекта

- **MVVM + Clean Architecture**
- **Jetpack Compose** (UI Layer)
- **ViewModel + StateFlow** (State Management)
- **Hilt** (Dependency Injection)
- **Navigation Compose** (безопасная навигация)
- **Coroutines / Flows** (асинхронность)
- **Room / DataStore** (опционально — сохранение очков)

---

## 📱 Структура экранов

- **Splash Screen** — два стартовых экрана логотип с анимацией (KSU Games + анамация мозга)
- **Главный экран (Home)**  
   - Загрузка фото родителей ("Мамка", "Папка")
   - Кнопка **НАЧАТЬ!**  
   - Счёт участников
- **Настройки (Drawer)**
   - Выбор стиля карточек  
   - Тумблеры: Звук, Заставка, Фон, Обнулить счёт
- **Выбор режима (Select Game Type)**
   - Режимы: EASY / MEDIUM / HARD / SPECIAL
- **Игра (Game Screen)**
   - Раскладка карточек 4x3  
   - Визуальные элементы: ? карточки + открытые изображения  
   - Возврат ← назад
- **Процесс игры (раунды)** 
   - Поиск совпадений  
   - Переход на следующий раунд по нажатию

---

## 🛠️ Технологии и стек

| Категория              | Использовано                                        |
|------------------------|-----------------------------------------------------|
| Язык                   | Kotlin                                              |
| UI-фреймворк           | Jetpack Compose                                     |
| Архитектура            | MVVM + Clean Architecture                           |
| Состояние              | Compose state, ViewModel + Flow                     |
| DI                     | Hilt                                                |
| Асинхронность          | Kotlin Coroutines                                   |
| Навигация              | Navigation Compose                                  |
| Состояние              | ViewModel + StateFlow                               |
| Анимации               | Compose Animations API                              |
| Хранение               | Room / SharedPreferences                            |
| Сборка                 | Gradle (KTS)                                        |

---
