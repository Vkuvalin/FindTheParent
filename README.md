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



























