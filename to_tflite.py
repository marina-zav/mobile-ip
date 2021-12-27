import tensorflow as tf

TF_PATH = "celeb_model3.pb" # where the saved_model is stored - but folder name
TFLITE_PATH = "./celeb_model_3.tflite"

# make a converter object from the saved tensorflow file
converter = tf.lite.TFLiteConverter.from_saved_model(TF_PATH)

converter.experimental_new_converter = True

converter.target_spec.supported_ops = [tf.lite.OpsSet.TFLITE_BUILTINS,
                                       tf.lite.OpsSet.SELECT_TF_OPS]

tf_lite_model = converter.convert()
# Save the model.
open(TFLITE_PATH, "wb").write(tf_lite_model)
