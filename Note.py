import json
import datetime
from random import choice

def save_notes(notes: list):
    with open("notes.json", "w", encoding="utf-8") as f:
        json.dump(notes, f)

def load_notes():
    try:
        with open("notes.json", "r", encoding="utf-8") as f:
            notes = json.load(f)
    except FileNotFoundError:
        notes = []
    return notes

def add_note():
    notes = load_notes()
    id = len(notes) + 1
    title = input("Введите заголовок заметки: ")
    body = input("Введите текст заметки: ")
    timestamp = str(datetime.datetime.now())

    note = {
        "id": id,
        "title": title,
        "body": body,
        "timestamp": timestamp
    }

    notes.append(note)
    save_notes(notes)
    print("Заметка успешно добавлена!")

def edit_note():
    notes = load_notes()
    id = int(input("Введите id заметки для редактирования: "))

    for note in notes:
        if note["id"] == id:
            title = input("Введите новый заголовок заметки: ")
            body = input("Введите новый текст заметки: ")
            timestamp = str(datetime.datetime.now())

            note["title"] = title
            note["body"] = body
            note["timestamp"] = timestamp
            save_notes(notes)
            print("Заметка успешно отредактирована!")
            return
    print("Заметка с таким id не найдена!")

def delete_note():
    notes = load_notes()
    id = int(input("Введите id заметки для удаления: "))

    for note in notes:
        if note["id"] == id:
            notes.remove(note)
            save_notes(notes)
            print("Заметка успешно удалена!")
            return
    print("Заметка с таким id не найдена!")

def print_notes():
    notes = load_notes()
    for note in notes:
        print("ID: ", note["id"])
        print("Заголовок: ", note["title"])
        print("Текст: ", note["body"])
        print("Дата/Время: ", note["timestamp"])
        print("----------------------")

def main():
    while True:
        print("1. Добавить заметку")
        print("2. Редактировать заметку")
        print("3. Удалить заметку")
        print("4. Показать заметки")
        print("5. Выход")

        choice = input("Выберите действие: ")

        if choice == "1":
            add_note()
        elif choice == "2":
            edit_note()
        elif choice == "3":
            delete_note()
        elif choice == "4":
            print_notes()
        elif choice == "5":
            break
        else:
            print("Некорректный ввод. Попробуйте снова!")

if __name__ == "__main__":
    main()
