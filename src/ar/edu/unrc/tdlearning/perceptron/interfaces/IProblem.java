/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.tdlearning.perceptron.interfaces;

import java.util.ArrayList;

/**
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public interface IProblem {

    /**
     *
     * @return el Actor (jugador si es un juego) que se va a entrenar
     */
    public IActor getActorToTrain();

    /**
     *
     * @param output salida del perceptron no normalizada.<p>
     * @param actor  jugador actual que necesita interpretar {@code output}
     *               desde su punto de vista
     * <p>
     * @return un valor representativo que interpreta la salida del perceptron
     *         {@code output} desde el punto de vista del jugador {@code player}
     */
    public IsolatedComputation<Double> computeNumericRepresentationFor(Object[] output, IActor actor);

    /**
     *
     * @param value <p>
     * @return
     */
    public double denormalizeValueFromPerceptronOutput(Object value);

    /**
     *
     * @param finalState
     * @param outputNeuron <p>
     * @return
     */
    public double getFinalReward(IState finalState, int outputNeuron);

    /**
     * Se debe inicializar el problema y avanzar hasta el punto en el que el
     * {@code actor} le toque actuar por primera vez
     * <p>
     * @param actor que se va a aentrenar durante el problema.
     * <p>
     * @return Inicializa el problema y devuelve su estado inicial
     */
    public IState initialize(IActor actor);

    /**
     *
     * @param turnInitialState estado del poblema sobre el cual hacer calculos
     * <p>
     * @return una lista de todas las acciones validas que se pueden aplicar al
     *         estado s
     */
    public ArrayList<IAction> listAllPossibleActions(IState turnInitialState);

    /**
     * Calcula el estado intermedio del turno, que es el estado al que llega el
     * problema inmediatamente luego de aplicar la accion deterministica 'a',
     * pero antes de aplicar las acciones no deterministicas. Al computar el
     * afterstate se debe cargar la puntuacion parcial obtenida en
     * {@code turnInitialState} para ser utilizado en diferentes algorimos.
     * <p>
     * @param turnInitialState estado inicial
     * @param action           accion a aplicar
     * <p>
     * @return estado intermedio deterministico resulante de aplicar la accion
     *         'a' al estado 's', con su recompensa parcial en caso de ser
     *         utilizado el calculo acumulativo en TDLearning
     */
    public IState computeAfterState(IState turnInitialState, IAction action);

    //TODO actualizar descripcion!
    /**
     * Este metodo debe:
     * <p>
     * 1) crear un estado que llamaremos 'nextState', que comineza siendo igual
     * a @afterstate.
     * <p>
     * 2) modificar 'nextState' aplicandole las acciones no deterministicas que
     * se aplican luego de las acciones deterministicas realizadas en
     * <p>
     * @afterstate, hasta llegar al proximo estado (turno)
     * <p>
     * 3) retornar 'nextState'
     * <p>
     * @param afterstate estado de transicion (luego de aplicar una accion
     *                   deterministica al estado actual del problema)
     * <p>
     * @return el estado 'nextState', que representa el siguiente turno o
     *         siguiente estado inicial (ver descripcion para mas detalles)
     */
    public IState computeNextTurnStateFromAfterstate(IState afterstate);

    /**
     * Modifica el estado del problema para que el nuevo estado sea @newState.
     * En otras palabras, simboliza el final del turno anterior y se hacen
     * efectivos todos los movimientos de la IA y de lascciones no
     * deterministicas que le siguen.
     * <p>
     * @param nextTurnState nuevo estado inicial
     */
    public void setCurrentState(IState nextTurnState);

    /**
     * Prediccion realizada por el perceptron de que tan bueno es el estado
     * {@code state}.
     * <p>
     * @param state estado intermedio si se utiliza afterstate, o inicio de
     *              estado
     * <p>
     * @return prediccion del perceptron, normalizado (si la funcion de
     *         activacion necesita normalizacion).
     */
    public IsolatedComputation<Object[]> evaluateBoardWithPerceptron(IState state);

    /**
     *
     * @param value <p>
     * @return
     */
    public double normalizeValueToPerceptronOutput(Object value);

}
