/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.administracionBase.util;

import py.com.ping.administracionBase.cdi.LoginControlador;
import py.com.ping.administracionBase.ejb.LoginEJB;
import py.com.ping.administracionBase.jpa.BswSession;
import py.com.ping.utilitarios.utils.DateUtils;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author gcabello
 * @date 06/06/2011 Esta clase se encarga de verificar si el usuario ya se
 * encuentra logueado en el sistema en el caso que sea asi entonces se le
 * muestra directamente la pantalla de inicio sino se le muestra la ventana de
 * login.
 */

public class SessionFilter implements Filter {
	private int tamNombre = SessionListenerPing.CONTEXT_PATH.length();
    //Tiempo que se espera para verificar en la base de datos si ya se cerro la session, desde otro lado
    private static final int TAMANHO_VERIFICA_SESSION = 5;
    private BswSession bswSession;

    @EJB
    private LoginEJB loginEJB;
    @Inject
    private LoginControlador loginControlador;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
                req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String usuario = session.getAttribute(ApplicationConstant.USERNAME) != null
				? session.getAttribute(ApplicationConstant.USERNAME).toString()
				: null;
        String accessToken = ((HttpServletRequest) request).getParameter("access");
        if(usuario == null && accessToken != null){
            String username = "";
            loginControlador.setUsername(username);
            loginControlador.setAccesoViaToken(true);
            FacesContext ctx = FacesUtil.getFacesContext(req, res);
            loginControlador.setCtx(ctx.getExternalContext());
            loginControlador.login();
        }
		String sPage = req.getRequestURL().toString();

		String sPageURL = "/" + sPage.substring(sPage.indexOf(SessionListenerPing.CONTEXT_PATH),
				sPage.indexOf(SessionListenerPing.CONTEXT_PATH) + tamNombre);
		int nPageSize = sPage.substring(sPage.indexOf(SessionListenerPing.CONTEXT_PATH), sPage.length() - 1).length();

		if (puedeContinuarSession(session)) {
			if (usuario != null && (sPage.contains("login.xhtml") || nPageSize == tamNombre)) {
				res.sendRedirect(sPageURL + ApplicationConstant.PAGINIT);
			} else if (usuario == null && !sPage.contains("login.xhtml")) {
                req.getSession().setAttribute("redireccion", sPage);
                res.sendRedirect(sPageURL + ApplicationConstant.PAGLOGIN);
            }
            else {
				chain.doFilter(request, response);
			}

		} else {
			logout(session, res, sPageURL);
		}
	}

    @Override
    public void destroy() {
    }

    private boolean puedeContinuarSession(HttpSession session) {
        Date lastAccessed = new Date(session.getLastAccessedTime());
        Date now = new Date();
        if (DateUtils.diffMinEntreFechas(lastAccessed, now) > TAMANHO_VERIFICA_SESSION) {
            bswSession = loginEJB.getSessionActivaById(session);
            if (bswSession != null) {
                return bswSession.getFechaLogout() == null;
            } else {
                return true;
            }
        }
        return true;

    }

    private void logout(HttpSession hSession, HttpServletResponse res, String sPageURL) throws IOException, ServletException {
        loginEJB.actualizarSession(hSession);
        hSession.invalidate();
        res.sendRedirect(sPageURL + ApplicationConstant.PAGLOGIN);
    }
}
