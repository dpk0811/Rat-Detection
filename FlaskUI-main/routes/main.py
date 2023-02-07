from flask import Blueprint, render_template

main = Blueprint('main', __name__)

@main.route('/')
def index():
    return render_template('index.html')


@main.route('/rat_page')
def rat_page():
    return render_template('rat_page.html')