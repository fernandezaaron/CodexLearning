import sys
import joblib
import os
from sklearn.tree import DecisionTreeClassifier

dtree_model = joblib.load(os.getcwd() + '\\assets\\model\\codeRiddleML.sav')

# dtree_model = pickle.load(open('Dtree.sav', 'rb'))

result = dtree_model.predict([[sys.argv[1], sys.argv[2]]])

print(result)