package com.greymax.vkplayer.db.main.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("com.greymax.vkplayer.db.main.annotation.Transactional")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class TransactionalProcessor extends AbstractProcessor {

    /** public for ServiceLoader */
    public TransactionalProcessor() {

    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        //For each element annotated with the Handleable annotation
        for (Element e : roundEnv.getElementsAnnotatedWith(Transactional.class)) {

            //Check if the type of the annotated element is not a method. If yes, return a warning.
            if (e.getKind() != ElementKind.METHOD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.WARNING,
                        "Not a method", e);
                continue;
            }

            //Define the following variables: name and clazz.
            String name = capitalize(e.getSimpleName().toString());
            TypeElement clazz = (TypeElement) e.getEnclosingElement();

            try {
                // main block
                JavaFileObject f = processingEnv.getFiler().
                        createSourceFile(clazz.getQualifiedName() + "Extras");
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                        "Creating " + f.toUri());
                Writer w = f.openWriter();
                //Add the content to the newly generated file.
                try {
                    PrintWriter pw = new PrintWriter(w);
                    pw.println("package "
                            + clazz.getEnclosingElement().getSimpleName() + ";");
                    pw.println("public abstract class "
                            + clazz.getSimpleName() + "Extras {");
                    pw.println("    protected " + clazz.getSimpleName()
                            + "Extras() {}");
                    TypeMirror type = e.asType();
                    pw.println("    /** Handle something. */");
                    pw.println("    protected final void handle" + name
                            + "(" + type + " value) {");
                    pw.println("        System.out.println(value);");
                    pw.println("    }");
                    pw.println("}");
                    pw.flush();
                } finally {
                    w.close();
                }
            } catch (Exception ex) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.toString());
            }
        }
        return true;
    }

    private static String capitalize(String name) {
        char[] c = name.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        return new String(c);
    }
}
