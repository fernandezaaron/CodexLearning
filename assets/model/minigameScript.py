import sys
import joblib
import os
from sklearn.tree import DecisionTreeClassifier

# install joblib and sklearn in cmd
# load model
# result = model.predict([[sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5]])
# print result

dtree_model = joblib.load(os.getcwd() + '\\assets\\model\\minigameML.sav')

result = dtree_model.predict([[sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4]]])

print(result)