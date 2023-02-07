from routes import *
from routes.main import main
from models import *
import os


def create_app():
    basedir = os.path.abspath(os.path.dirname(__file__))
    app = Flask(__name__ , template_folder='templates')
     
    app.config.from_pyfile('config.cfg')
    Bootstrap(app)
    
    app.config['SQLALCHEMY_DATABASE_URI'] =\
        'sqlite:///' + os.path.join(basedir+'\databases', 'database.db')
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

    db.init_app(app)


    app.register_blueprint(main)
    return app

def db_init():
    app = create_app()
    db.init_app(app)
    app.test_request_context().push()
    db.create_all()

    db.session.commit()


if __name__ == '__main__':

    args  =  sys.argv
    
    if len(args) == 1: 
        print("Error: Plese provide one of positional arguments (run or db)")
        sys.exit()      
    elif args[1]== "run":
        app = create_app()
        
        # db_init()
        app.run(debug=True)
    elif args[1]=="db":
        db_init()


