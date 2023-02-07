from flask_sqlalchemy import SQLAlchemy
from .Rat import Rat
from .db  import db 

# db = SQLAlchemy() # ORM converts code written to desired db query. eg- sqllite, mysql etc

__all__ = [
    "db",
    "Rat",
    ]